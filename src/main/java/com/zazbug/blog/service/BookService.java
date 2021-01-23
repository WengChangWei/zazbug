package com.zazbug.blog.service;

import com.github.pagehelper.PageInfo;
import com.zazbug.blog.pojo.Book;

import java.util.List;

public interface BookService {

	/**
	 * 添加
	 * @param book
	 */
	public void add(Book book);

	/**
	 * 查询所有数据
	 * @return
	 */
	public List<Book> findAll();

	/**
	 * 条件+分页查询
	 * @param book
	 * @param page
	 * @param size
	 * @return
	 */
	public PageInfo<Book> findPage(Book book, int page, int size);

	/**
	 * 分页查询
	 * @param page
	 * @param size
	 * @return
	 */
	public PageInfo<Book> findPage(int page, int size);

	/**
	 * 条件查询
	 * @param book
	 * @return
	 */
	public List<Book> findList(Book book);

}
