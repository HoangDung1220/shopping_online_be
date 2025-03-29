package com.shoppingonline.base;

import java.util.List;

public interface BaseService<T, V> {
		
	public boolean prepare(T request, V response);
	
	public void save(T model);
	
	public List<T> findAll();
	
}
