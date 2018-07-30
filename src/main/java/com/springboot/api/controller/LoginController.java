package com.springboot.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.api.service.LoginService;

@RestController
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@RequestMapping(method = RequestMethod.GET)
	public String home(){		
		return "success";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public HashMap<String, Object> login(@RequestParam Map<String, String> params){	
		String username = params.get("username");
		String password = params.get("password");
		
		//Check Login
		HashMap<String, Object> result = loginService.loginUser(username, password);	
		
		return result;
	}
}
