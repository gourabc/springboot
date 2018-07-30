package com.springboot.api.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.api.dao.LoginDAO;

@Service
public class LoginService {
	
	@Autowired
	private LoginDAO loginDAOImpl;

	public HashMap<String, Object> loginUser(String username, String password){
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if(username!=null && !"".equals(username) && password!=null && !"".equals(password)) {			
			// Enter Code for DB Check
			boolean isValid =  loginDAOImpl.loginUser(username, password);
			
			if(isValid){				
				result.put("message", "Login successful");
			} else {
				result.put("message", "Invalid username or password");
			}		
		} else {			
			result.put("message", "Please enter valid username and password");
		}
		
		return result;
	}
}
