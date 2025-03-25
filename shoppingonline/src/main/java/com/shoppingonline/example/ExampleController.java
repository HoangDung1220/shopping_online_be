package com.shoppingonline.example;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingonline.base.BaseController;
import com.shoppingonline.utils.UserUtil;

@RestController
public class ExampleController extends BaseController<ExampleModel, String>{
	
	private MessageSource messageSource;
	
	public ExampleController(ExampleService exampleService, UserUtil userUtil, MessageSource messageSource) {
		super(exampleService, userUtil);
		this.messageSource = messageSource;
	}
	
	@GetMapping("/")
	public List<ExampleModel> getHome() {
		return service.findAll();
	}
	
    @GetMapping("/message")
    public String getMessage() {
    	setUser(userUtil.getUser().get());
        Locale locale = new Locale(getUser().getLang());
        return messageSource.getMessage("welcome.message", null, locale);
    }
	

}
