package com.xl.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.xl.spring.web.dao.Message;
import com.xl.spring.web.dao.MessagesDao;
import com.xl.spring.web.dao.User;
import com.xl.spring.web.dao.UserDao;

@Service("userService")
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MessagesDao messagesDao;

	public void create(User user){
		userDao.create(user);
	}

	public boolean exists(String username) {
		return userDao.exists(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	public void sendMessage(Message message){
		messagesDao.saveOrUpdate(message);
	}
	
	public User getUser(String username){
		return userDao.getUser(username);
	}
}
