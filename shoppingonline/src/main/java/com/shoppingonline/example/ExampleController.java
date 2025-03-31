package com.shoppingonline.example;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingonline.base.BaseController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@Tag(name="Example API", description = "Example API")
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
    @Operation(
            summary = "Example to get message",
            description = "Example to get message",
            responses = {
                @ApiResponse(responseCode = "201", description = "Success"),
                @ApiResponse(responseCode = "400", description = "Error")
            }
        )
    public String getMessage() {
        Locale locale = new Locale(service.getUser().getLang());
        return messageSource.getMessage("welcome.message", null, locale);
    }
	

}
