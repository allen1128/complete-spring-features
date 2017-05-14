package com.xl.spring.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xl.spring.web.dao.User;
import com.xl.spring.web.service.UserService;

@Controller
public class LoginController {
	
	UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/login")
	public String showLogin(){
		return "login";
	}	

	@RequestMapping("/loggedout")
	public String showLoggedOut(){
		return "loggedout";
	}	
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model){		
		model.addAttribute("user", new User());
		return "newaccount";
	}
	
	@RequestMapping(value="/createaccount", method = RequestMethod.POST)
	public String createAccount(Model model, @Valid User user, BindingResult result){
		if (result.hasErrors()){
			return "newaccount";
		}
		
		user.setEnabled(true);
		user.setAuthority("user");
		
		if(userService.exists(user.getUsername())){
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
		try {
			userService.create(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
		
		return "accountcreated";
	} 
}
