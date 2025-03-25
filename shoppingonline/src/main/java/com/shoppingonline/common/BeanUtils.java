package com.shoppingonline.common;

import com.shoppingonline.constant.Constant;

public class BeanUtils {
	
	public static boolean isEmpty(String str) {
		return str == null || Constant.BLANK.equals(str);
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
}
