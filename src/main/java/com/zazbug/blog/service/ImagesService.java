package com.zazbug.blog.service;

import com.github.pagehelper.PageInfo;
import com.zazbug.blog.pojo.Images;

import java.util.List;

public interface ImagesService {

	public List<Images> findAll();

	public Images findById(Integer id);

	public void add(Images images);

	public void update(Images images);

	public PageInfo<Images> findPage(int page, int size);

	public PageInfo<Images> findPage(Images images, int page, int size);

}
