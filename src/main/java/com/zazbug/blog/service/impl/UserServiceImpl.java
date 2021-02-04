package com.zazbug.blog.service.impl;

import com.zazbug.blog.mapper.UserMapper;
import com.zazbug.blog.pojo.User;
import com.zazbug.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByUsername(String username) {
		User user = userMapper.selectByPrimaryKey(username);
		return user;
	}

	@Override
	public void update(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

}
