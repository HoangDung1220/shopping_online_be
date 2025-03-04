package com.shoppingonline.example;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
	
	private ExampleService exampleService;
	
	public ExampleController(ExampleService exampleService) {
		this.exampleService = exampleService;
	}
	
	@GetMapping("/")
	public List<ExampleModel> getHome() {
		return exampleService.getAllExamples();
	}

}
