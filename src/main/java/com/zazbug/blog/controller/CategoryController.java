package com.zazbug.blog.controller;

import com.github.pagehelper.PageInfo;
import com.zazbug.blog.entity.Result;
import com.zazbug.blog.entity.StatusCode;
import com.zazbug.blog.pojo.Category;
import com.zazbug.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类控制器
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * 查询所有
	 * @return
	 */
	@GetMapping(value = "/all")
	public Result<List<Category>> findAll(){
		List<Category> all = categoryService.findAll();
		return new Result<List<Category>>(true, StatusCode.OK,"查询成功",all);
	}

	@GetMapping(value = "/findAllAndChildren")
	public Result findAllAndChildren(){
		List<Category> allAndChildren = categoryService.findAllAndChildren();
		return new Result<List<Category>>(true, StatusCode.OK,"查询成功",allAndChildren);
	}

	/**
	 * 添加分类
	 * @param category
	 * @return
	 */
	@PostMapping
	public Result add(@RequestBody Category category){
		categoryService.add(category);
		return new Result(true, StatusCode.OK,"添加成功");
	}

	/**
	 * 修改分类
	 * @param category
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/{id}")
	public Result update(@RequestBody Category category,@PathVariable Integer id){
		category.setId(id);
		categoryService.update(category);
		return new Result(true, StatusCode.OK,"修改成功");
	}

	/**
	 * 通过id查询分类信息
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public Result<Category> findById(@PathVariable Integer id){
		Category byId = categoryService.findById(id);
		return new Result<Category>(true, StatusCode.OK,"查询成功",byId);
	}

	/**
	 * 分页
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value = "/search/{page}/{size}")
	public Result findPage(@PathVariable int page, @PathVariable int size){
		PageInfo<Category> pageInfo = categoryService.findPage(page, size);
		return new Result(true, StatusCode.OK,"查询成功",pageInfo);
	}

	/**
	 * 查询分类所属下级分类
	 * @param parentId
	 * @return
	 */
	@GetMapping(value = "/byParentId/{parentId}")
	public Result<Category> findByParentId(@PathVariable int parentId){
		return null;
	}

}
