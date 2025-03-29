package com.shoppingonline.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.shoppingonline.security.UserDTO;
import com.shoppingonline.utils.UserUtil;

public abstract class BaseService<T, V> {
	
	protected UserDTO user;
	
	protected V response;

	@Autowired
	protected UserUtil userUtil;
	
	public void setUserUtil(UserUtil userUtil){
		this.userUtil = userUtil;
	}
	
	public boolean prepare(T request, V response) {
		return true;
	}
	
	public void save(T model) {}
	
	public List<V> findAll(){
		return null;
	}
	
	public UserDTO getUser() {
		if (userUtil.getUser().isPresent()) {
			return userUtil.getUser().get();
		}
		return null;
	}
	
}
