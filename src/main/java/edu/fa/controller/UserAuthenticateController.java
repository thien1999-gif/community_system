package edu.fa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.fa.security.UserSecuritySuccessHandler;

@Controller
public class UserAuthenticateController {

	@Autowired
	private UserSecuritySuccessHandler startUp;
	
	
	@RequestMapping(value = "/")
	public void displayLogin2(HttpServletRequest request, HttpServletResponse response) { 
		try {
			startUp.handle(request, response, getRoleInRememberMeAuthenticated());
		} catch (Exception e) {
			e.printStackTrace();
		}
				    
	}
	private Authentication getRoleInRememberMeAuthenticated() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
