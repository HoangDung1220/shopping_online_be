package com.shoppingonline.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shoppingonline.constant.Constant;

public class CheckValidate {
	
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    
	public static String isNum(String value) {
		for (int i = 0 ; i<value.length(); i++) {
			if (value.charAt(i) < '0' || value.charAt(i) > '9') {
				return Constant.ERROR_AE00101;
			}
		}
		return Constant.AE00000;		
	}

	public static String isMix(String value, boolean isRequired, int length, boolean isFullDigits, boolean isSpace) {
		if (isRequired && BeanUtils.isEmpty(value)) {
			return Constant.ERROR_AE00102;
		}
		
		if (BeanUtils.nvl(value).length() > length && length != -1) {
			return Constant.ERROR_AE00103;
		} 
		
		if (BeanUtils.nvl(value).length() < length && isFullDigits) {
			return Constant.ERROR_AE00104;
		}
		
		if (!isSpace && value.contains(Constant.SPACE)) {
			return Constant.ERROR_AE00105;
		}
		return Constant.AE00000;		
	}
	
	public static String isEmail(String value) {
        Matcher matcher = EMAIL_PATTERN.matcher(value);
        if (!matcher.matches()) {
        	return Constant.ERROR_AE00106;
        }
        return Constant.AE00000;
	}
}
