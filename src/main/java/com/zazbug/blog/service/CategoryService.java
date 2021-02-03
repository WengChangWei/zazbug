package com.zazbug.blog.service;

import com.github.pagehelper.PageInfo;
import com.zazbug.blog.pojo.Category;

import java.util.List;

public interface CategoryService {

	public List<Category> findAll();

	public Category findById(Integer id);

	public void add(Category category);

	public void update(Category category);

	public PageInfo<Category> findPage(int page, int size);

	public PageInfo<Category> findPage(Category category, int page, int size);

	public List<Category> findAllAndChildren();

}
