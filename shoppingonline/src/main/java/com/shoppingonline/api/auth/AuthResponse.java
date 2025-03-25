package com.shoppingonline.api.auth;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
	
	private String status;
	private String token;
	private List<AuthErrorItem> errorItems;
}
