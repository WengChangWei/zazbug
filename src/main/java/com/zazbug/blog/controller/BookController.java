package com.zazbug.blog.controller;

import com.github.pagehelper.PageInfo;
import com.zazbug.blog.entity.Result;
import com.zazbug.blog.entity.StatusCode;
import com.zazbug.blog.pojo.Book;
import com.zazbug.blog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章控制器
 */
@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping
	public Result<List<Book>> findList(){
		List<Book> bookList = bookService.findAll();
		return new Result<List<Book>>(true,StatusCode.OK,"获取成功",bookList);
	}

	@PostMapping
	public Result add(@RequestBody Book book){
		bookService.add(book);
		return new Result<>(true,StatusCode.OK,"添加成功");
	}

	@PostMapping(value = "/search/{page}/{size}")
	public Result<PageInfo> findPage(@RequestBody(required = false) Book book, @PathVariable int page, @PathVariable int size){
		PageInfo<Book> bookPageInfo = bookService.findPage(book, page, size);
		return new Result<>(true,StatusCode.OK,"查询成功",bookPageInfo);
	}

}
