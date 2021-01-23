package com.zazbug.blog.service;

import com.zazbug.blog.pojo.User;

public interface UserService {

	public User findByUsername(String username);

}
