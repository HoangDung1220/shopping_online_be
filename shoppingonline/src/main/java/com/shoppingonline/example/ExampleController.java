package com.shoppingonline.example;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingonline.base.BaseController;

@RestController
public class ExampleController extends BaseController<ExampleModel, ExampleModel>{
	
	private MessageSource messageSource;
	
	public ExampleController(ExampleService exampleService, MessageSource messageSource) {
		super(exampleService);
		this.messageSource = messageSource;
	}
	
	@GetMapping("/")
	public List<ExampleModel> getHome() {
		return service.findAll();
	}
	
    @GetMapping("/message")
    public String getMessage() {
        Locale locale = new Locale(service.getUser().getLang());
        return messageSource.getMessage("welcome.message", null, locale);
    }
	

}
