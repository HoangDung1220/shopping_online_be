package com.shoppingonline.api.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
	
	private String email;
	private String password;
	private String lang;
	private String type;
	private String action;

}
