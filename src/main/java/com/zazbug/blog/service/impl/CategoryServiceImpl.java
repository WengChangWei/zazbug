package com.zazbug.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zazbug.blog.mapper.CategoryMapper;
import com.zazbug.blog.pojo.Category;
import com.zazbug.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> findAll() {
		List<Category> categories = categoryMapper.selectAll();
		return categories;
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
		categoryMapper.updateByPrimaryKey(category);
	}

	@Override
	public PageInfo<Category> findPage(int page, int size) {
		PageHelper.startPage(page,size);
		return new PageInfo<Category>(categoryMapper.selectAll());
	}
}
