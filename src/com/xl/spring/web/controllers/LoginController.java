package com.xl.spring.web.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xl.spring.web.dao.FormValidationGroup;
import com.xl.spring.web.dao.Message;
import com.xl.spring.web.dao.User;
import com.xl.spring.web.service.UserService;

@Controller
public class LoginController {

	UserService userService;
	
	@Autowired
	MailSender mailSender;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "admin";
	}

	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}

	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user", new User());
		return "newaccount";
	}

	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}
	
	@RequestMapping("/messages")
	public String showMessages() {
		return "messages";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String createAccount(Model model, @Validated(FormValidationGroup.class) User user, BindingResult result) {
		if (result.hasErrors()) {
			return "newaccount";
		}

		user.setEnabled(true);
		user.setAuthority("ROLE_USER");

		if (userService.exists(user.getUsername())) {
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

	@RequestMapping(value = "getmessages", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getMessages(Principal principal) {
		Map<String, Object> data = new HashMap<String, Object>();
		List<Message> messages = null;

		if (principal == null) {
			messages = new ArrayList<>();
		} else {
			String username = principal.getName();
			messages = userService.getMessages(username);
		}
		data.put("messages", messages);
		data.put("number", messages.size());
		return data;
	}
	
	@RequestMapping(value = "sendreply", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> sendReply(Principal principal, @RequestBody Map<String, Object> data) {
		String text = (String) data.get("text");
		String name = (String) data.get("name");
		String email = (String) data.get("email");
		
		SimpleMailMessage emailMsg = new SimpleMailMessage();
		emailMsg.setFrom("as.allen1128@gmail.com");
		emailMsg.setTo(email);
		emailMsg.setSubject("Re:" + name + ", your message");
		emailMsg.setText(text);
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		try {
			mailSender.send(emailMsg);
			res.put("success", true);
			res.put("target", data.get("target"));
		} catch (Exception exception){
			exception.printStackTrace();
			res.put("error", true);
		}
		
		return res;
	}
	
}
