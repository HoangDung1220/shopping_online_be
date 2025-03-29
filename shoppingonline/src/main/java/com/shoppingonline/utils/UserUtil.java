package com.shoppingonline.utils;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.shoppingonline.security.UserDTO;

@Component
public class UserUtil {
	
	public Optional<UserDTO> getUser() {
		return getAuthentication()
				.map(Authentication::getPrincipal)
				.filter(principal -> principal instanceof UserDTO)
				.map(UserDTO.class::cast);
	}
	
	public boolean isAuthenticated() {
		return getAuthentication().map(Authentication::isAuthenticated).orElse(false);
	}
	
	private Optional<Authentication> getAuthentication(){
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
	}

}
