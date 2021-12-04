package edu.fa.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import edu.fa.dto.UserAuthenticateDetails;

public class SecurityUtils {
	
	public static UserAuthenticateDetails getPrincipal() {
		UserAuthenticateDetails newUser = (UserAuthenticateDetails)(SecurityContextHolder
							.getContext()
							.getAuthentication()
							.getPrincipal());
		return newUser;
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getAuthorities(){
		List<String> results = new ArrayList<String>();
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) (SecurityContextHolder
																		.getContext()
																		.getAuthentication()
																		.getAuthorities());
		for (GrantedAuthority grantedAuthority : authorities) {
			results.add(grantedAuthority.getAuthority());
		}
		return results;
	}
}
