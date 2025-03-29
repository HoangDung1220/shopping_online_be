package com.shoppingonline.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.shoppingonline.common.BeanUtils;
import com.shoppingonline.constant.Constant;

@Component
public class MessageUtil {

	private MessageSource messageSource;
	
	@Autowired
	private void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public String getMessage(String code, String lang) {
		if (BeanUtils.isEmpty(lang)) {
			lang = Constant.EN;
		}
		Locale locale = new Locale(lang);
		return messageSource.getMessage(code, null, locale);
	}
}
