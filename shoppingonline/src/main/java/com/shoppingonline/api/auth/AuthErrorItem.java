package com.shoppingonline.api.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthErrorItem {
	private String errorItem;
	private String errorMessage;
}
