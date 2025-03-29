package com.shoppingonline.base;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.shoppingonline.security.UserDTO;
import com.shoppingonline.utils.UserUtil;

@Component
public abstract class BaseController<T, V> {
    
	protected final BaseService<T, V> service;

	protected final UserUtil userUtil;
	
	private Optional<UserDTO> user;
	
	public BaseController(BaseService<T, V> service, UserUtil userUtil) {
		this.service = service;
		this.userUtil = userUtil;
	}
	
	protected void setUser(Optional<UserDTO> user) {
		this.user = user;
	}

	protected Optional<UserDTO> getUser() {
		return this.user;
	}	

}
