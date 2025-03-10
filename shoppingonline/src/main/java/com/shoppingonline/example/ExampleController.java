package com.shoppingonline.example;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ExampleController {
	
	private ExampleService exampleService;
	
	@Autowired
	private MessageSource messageSource;
	
	public ExampleController(ExampleService exampleService) {
		this.exampleService = exampleService;
	}
	
	@GetMapping("/")
	public List<ExampleModel> getHome() {
		return exampleService.getAllExamples();
	}
	
    @GetMapping("/message")
    public String getMessage(@RequestParam(name="lang1",defaultValue = "en") String lang) {
        Locale locale = new Locale(lang);
        return messageSource.getMessage("welcome.message", null, locale);
    }
	

}
