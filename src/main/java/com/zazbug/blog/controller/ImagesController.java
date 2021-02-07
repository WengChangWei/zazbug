package com.zazbug.blog.controller;

import com.github.pagehelper.PageInfo;
import com.zazbug.blog.entity.Result;
import com.zazbug.blog.entity.StatusCode;
import com.zazbug.blog.pojo.Images;
import com.zazbug.blog.service.ImagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/images")
public class ImagesController {

	@Autowired
	private ImagesService imagesService;

	/**
	 * 查询所有
	 * @return
	 */
	@GetMapping(value = "/all")
	public Result<List<Images>> findAll(){
		List<Images> all = imagesService.findAll();
		return new Result<List<Images>>(true, StatusCode.OK,"查询成功",all);
	}

	/**
	 * 添加分类
	 * @param images
	 * @return
	 */
	@PostMapping
	public Result add(@RequestBody Images images){
		imagesService.add(images);
		return new Result(true, StatusCode.OK,"添加成功");
	}

	/**
	 * 修改分类
	 * @param images
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/{id}")
	public Result update(@RequestBody Images images,@PathVariable Integer id){
		images.setId(id);
		imagesService.update(images);
		return new Result(true, StatusCode.OK,"修改成功");
	}

	/**
	 * 通过id查询分类信息
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public Result<Images> findById(@PathVariable Integer id){
		Images byId = imagesService.findById(id);
		return new Result<Images>(true, StatusCode.OK,"查询成功",byId);
	}

	/**
	 * 分页
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value = "/search/{page}/{size}")
	public Result findPage(@PathVariable int page, @PathVariable int size){
		PageInfo<Images> pageInfo = imagesService.findPage(page, size);
		return new Result(true, StatusCode.OK,"查询成功",pageInfo);
	}

}
