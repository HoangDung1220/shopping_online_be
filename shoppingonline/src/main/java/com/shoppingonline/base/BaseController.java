package com.shoppingonline.base;

import org.springframework.stereotype.Component;

@Component
public abstract class BaseController<T, V> {
    
	protected final BaseService<T, V> service;
		
	public BaseController(BaseService<T, V> service) {
		this.service = service;
	}	

}
