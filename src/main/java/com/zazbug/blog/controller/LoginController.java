package com.zazbug.blog.controller;

import com.zazbug.blog.entity.BCrypt;
import com.zazbug.blog.entity.JwtTokenUtils;
import com.zazbug.blog.entity.Result;
import com.zazbug.blog.entity.StatusCode;
import com.zazbug.blog.pojo.User;
import com.zazbug.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtils jwtTokenUtils;

	@Autowired
	@Qualifier("jwtUserDetailsService")
	private UserDetailsService userDetailsService;

	@PostMapping("/login")
	public Result login(@RequestBody User user){
		User userServiceByUsername = userService.findByUsername(user.getUsername());
		if(userServiceByUsername == null){
			return new Result(false, StatusCode.LOGINERROR, "用户名错误");
		}

		if(BCrypt.checkpw(user.getPassword(),userServiceByUsername.getPassword())){
			// 成功
			UserDetails userDetails = userDetailsService.loadUserByUsername(userServiceByUsername.getUsername());
			String token = jwtTokenUtils.generateToken(userDetails);
			return new Result(true, StatusCode.OK, "登陆成功!",token);

		}else{
			return new Result(false, StatusCode.LOGINERROR,"密码错误");
		}
	}

}
