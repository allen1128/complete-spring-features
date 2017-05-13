package com.xl.spring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xl.spring.web.dao.UserDao;
import com.xl.spring.web.dao.User;

@Service("userService")
public class UserService {
	
	UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void create(User user){
		userDao.create(user);
	}

	public boolean exists(String username) {
		return userDao.exists(username);
	}

}
