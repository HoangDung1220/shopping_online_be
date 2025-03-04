package com.shoppingonline.example;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ExampleService {
	
	private ExampleRepository exampleRepository;
	
	public ExampleService(ExampleRepository exampleRepository) {
		this.exampleRepository = exampleRepository;
	}
	
	public List<ExampleModel> getAllExamples(){
		return exampleRepository.findAll();
	}
	
	public ExampleModel getById(Long id){
		return exampleRepository.getOne(id);
	}
}
