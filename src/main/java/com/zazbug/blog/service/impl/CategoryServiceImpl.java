package com.zazbug.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zazbug.blog.mapper.CategoryMapper;
import com.zazbug.blog.pojo.Category;
import com.zazbug.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> findAll() {
		// List<Category> categories = categoryMapper.selectAll();
		Category category = new Category();
		category.setParentId(0);
		List<Category> categories = categoryMapper.selectByExample(createExample(category));
		List<Category> children = findChildren(categories, category);
		return children;
	}

	@Override
	public Category findById(Integer id) {
		Category category = categoryMapper.selectByPrimaryKey(id);
		return category;
	}

	@Override
	public void add(Category category) {
		categoryMapper.insert(category);
	}

	@Override
	public void update(Category category) {
		categoryMapper.updateByPrimaryKeySelective(category);
	}

	@Override
	public PageInfo<Category> findPage(int page, int size) {
		PageHelper.startPage(page,size);
		Example example = new Example(Category.class);
		example.setOrderByClause("id desc");
		List<Category> categories = categoryMapper.selectByExample(example);
		for (Category category : categories) {
			if(category.getParentId() > 0){
				Category byId = findById(category.getParentId());
				category.setParentCate(byId);
			}else{
				Category category1 = new Category();
				category1.setName("顶级分类");
				category.setParentCate(category1);
			}
		}
		return new PageInfo<Category>(categories);
	}

	@Override
	public PageInfo<Category> findPage(Category category, int page, int size) {
		PageHelper.startPage(page,size);
		List<Category> categoryList = categoryMapper.selectByExample(createExample(category));
		List<Category> children = findChildren(categoryList, category);
		return new PageInfo<Category>(children);
	}

	@Override
	public List<Category> findAllAndChildren(){
		Category category = new Category();
		category.setParentId(0);
		category.setIsShow("1");
		// 一级分类
		List<Category> categories = categoryMapper.selectByExample(createExample(category));
		// 二级分类
		List<Category> children = findChildren(categories, category);
		return children;
	}

	/**
	 * 查询子分类
	 * @param categoryList
	 * @param category
	 * @return
	 */
	private List<Category> findChildren(List<Category> categoryList, Category category){
		for (Category cate : categoryList) {
				category.setParentId(cate.getId());
				List<Category> categories = categoryMapper.selectByExample(createExample(category));
				cate.setChildren(categories);
		}
		return categoryList;
	}

	private Example createExample(Category category){
		Example example = new Example(Category.class);
		example.setOrderByClause("sort desc");
		Example.Criteria criteria = example.createCriteria();
		if(category == null) return example;
		if("0".equals(String.valueOf(category.getIsShow())) || "1".equals(String.valueOf(category.getIsShow()))) criteria.andEqualTo("isShow",category.getIsShow());
		if("0".equals(String.valueOf(category.getParentId())) || "null".equals(String.valueOf(category.getParentId())) || category.getParentId() > 0) criteria.andEqualTo("parentId",category.getParentId());
		return example;
	}
}
