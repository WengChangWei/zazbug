package com.zazbug.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zazbug.blog.mapper.ImagesMapper;
import com.zazbug.blog.pojo.Category;
import com.zazbug.blog.pojo.Images;
import com.zazbug.blog.service.CategoryService;
import com.zazbug.blog.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
@Service
public class ImagesServiceImpl implements ImagesService {

	@Autowired
	private ImagesMapper imagesMapper;

	@Autowired
	private CategoryService categoryService;

	@Override
	public List<Images> findAll() {
		List<Images> images = imagesMapper.selectAll();
		return images;
	}

	@Override
	public Images findById(Integer id) {
		Images images = new Images();
		images.setId(id);
		Images images1 = imagesMapper.selectByPrimaryKey(images);
		return images1;
	}

	@Override
	public void add(Images images) {
		images.setAddTime(new Date());
		imagesMapper.insertSelective(images);
	}

	@Override
	public void update(Images images) {
		imagesMapper.updateByPrimaryKey(images);
	}

	@Override
	public PageInfo<Images> findPage(int page, int size) {
		PageHelper.startPage(page,size);
		Example example = new Example(Images.class);
		example.setOrderByClause("id desc");
		// List<Images> images = imagesMapper.selectAll();
		List<Images> images = imagesMapper.selectByExample(example);
		for (Images image : images) {
			Category category = categoryService.findById(image.getCateId());
			image.setCategory(category);
		}
		return new PageInfo<Images>(images);
	}
}