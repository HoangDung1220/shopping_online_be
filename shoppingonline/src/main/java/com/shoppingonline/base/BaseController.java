package com.shoppingonline.base;

import com.shoppingonline.security.UserDTO;
import com.shoppingonline.utils.UserUtil;

public abstract class BaseController<T, ID> {
    
	protected final BaseService<T, ID> service;

	protected final UserUtil userUtil;
	
	private UserDTO user;
	
	public BaseController(BaseService<T, ID> service, UserUtil userUtil) {
		this.service = service;
		this.userUtil = userUtil;
	}
	
	protected void setUser(UserDTO user) {
		this.user = user;
	}

	protected UserDTO getUser() {
		return this.user;
	}	

}
