package com.shoppingonline.base;

import java.util.List;

public interface BaseService<T, ID> {
		
	public boolean prepare(T model);
	
	public void save(T model);
	
	public List<T> findAll();
	
}
