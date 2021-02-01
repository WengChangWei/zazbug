package com.zazbug.blog.controller;

import com.zazbug.blog.entity.Result;
import com.zazbug.blog.entity.StatusCode;
import com.zazbug.blog.pojo.User;
import com.zazbug.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/findByUsername")
	public Result<User> findByUsername(String username){
		User userServiceByUsername = userService.findByUsername(username);
		return new Result<User>(true, StatusCode.OK,"查询成功",userServiceByUsername);
	}

	@GetMapping
	public Result getUserInfo(){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		User userServiceByUsername = userService.findByUsername(username);
		return new Result(true, StatusCode.OK,"获取成功",userServiceByUsername);
	}

}
