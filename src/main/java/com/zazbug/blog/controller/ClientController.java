package com.zazbug.blog.controller;

import com.github.pagehelper.PageInfo;
import com.zazbug.blog.entity.Result;
import com.zazbug.blog.entity.StatusCode;
import com.zazbug.blog.pojo.Category;
import com.zazbug.blog.pojo.Images;
import com.zazbug.blog.pojo.User;
import com.zazbug.blog.service.CategoryService;
import com.zazbug.blog.service.ImagesService;
import com.zazbug.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端访问接口
 */
@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ImagesService imagesService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	/**
	 * 按分类获取图片
	 * @return
	 */
	@GetMapping("/images/list/{cateId}/{page}/{size}")
	public Result getImagesList(@PathVariable Integer cateId, @PathVariable Integer page, @PathVariable Integer size){
		Images images = new Images();
		images.setCateId(cateId);
		images.setIsShow("1");
		PageInfo<Images> imagesServicePage = imagesService.findPage(images, page, size);
		return new Result(true, StatusCode.OK,"获取成功",imagesServicePage);
	}

	/**
	 * 获取分类
	 * @return
	 */
	@GetMapping("/category/list/{page}/{size}")
	public Result getCateList(@PathVariable int page, @PathVariable int size){
		Category category = new Category();
		category.setIsShow("1");
		PageInfo<Category> cateList = categoryService.findPage(category, page, size);
		return new Result(true, StatusCode.OK,"获取成功",cateList);
	}

	/**
	 * 获取博主信息
	 * @return
	 */
	@GetMapping("/userInfo")
	public Result getUserInfo(){
		User user = userService.findByUsername("zazbug");
		user.setPassword("");
		return new Result(true, StatusCode.OK,"获取成功",user);
	}

}
