package com.shoppingonline.example;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shoppingonline.base.BaseService;

@Service
public class ExampleService implements BaseService<ExampleModel, ExampleModel>{
	
	private ExampleRepository exampleRepository;
	
	public ExampleService(ExampleRepository exampleRepository) {
		this.exampleRepository = exampleRepository;
	}

	@Override
	public boolean prepare(ExampleModel model, ExampleModel model1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void save(ExampleModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ExampleModel> findAll() {
		return exampleRepository.findAll();
	}
}
