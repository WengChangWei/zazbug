package com.zazbug.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zazbug.blog.mapper.BookMapper;
import com.zazbug.blog.pojo.Book;
import com.zazbug.blog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper bookMapper;

	/**
	 * 添加
	 * @param book
	 */
	@Override
	public void add(Book book) {
		bookMapper.insert(book);
	}

	/**
	 * 获取所有数据
	 * @return
	 */
	@Override
	public List<Book> findAll() {
		List<Book> books = bookMapper.selectAll();
		return books;
	}

	/**
	 * 条件+分页查询
	 * @param book
	 * @param page
	 * @param size
	 * @return
	 */
	@Override
	public PageInfo<Book> findPage(Book book, int page, int size) {
		// 分页
		PageHelper.startPage(page,size);
		// 搜索条件
		Example example = createExample(book);
		return new PageInfo<Book>(bookMapper.selectByExample(example));
	}

	/**
	 * 分页查询
	 * @param page
	 * @param size
	 * @return
	 */
	@Override
	public PageInfo<Book> findPage(int page, int size) {
		PageHelper.startPage(page,size);
		return new PageInfo<Book>(bookMapper.selectAll());
	}

	/**
	 * 条件查询
	 * @param book
	 * @return
	 */
	@Override
	public List<Book> findList(Book book) {
		// 搜索条件
		Example example = createExample(book);
		return bookMapper.selectByExample(example);
	}

	private Example createExample(Book book){
		Example example = new Example(Book.class);
		Example.Criteria criteria = example.createCriteria();
		if(book == null) return example;
		if(StringUtils.isEmpty(book.getId())) criteria.andEqualTo("id",book.getId());
		if(StringUtils.isEmpty(book.getAdmin_id())) criteria.andEqualTo("admin_id",book.getAdmin_id());
		if(StringUtils.isEmpty(book.getAddTime())) criteria.andEqualTo("admin_id",book.getAddTime());
		return example;
	}

}
