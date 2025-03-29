package com.shoppingonline.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.shoppingonline.utils.MessageUtil;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final CustomUserDetailsService customUserDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	private MessageUtil messageUtil;
	
	public CustomAuthenticationProvider(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder, MessageUtil messageUtil) {
		this.customUserDetailsService = customUserDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.messageUtil = messageUtil;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		UserDTO userDto = (UserDTO) customUserDetailsService.loadUserByUsername(email);
		
        if (!passwordEncoder.matches(password, userDto.getPassword())) {
            throw new BadCredentialsException(messageUtil.getMessage("error.AE00002", userDto.getLang()));
        }
        
        if (!userDto.isEnabled()) {
            throw new BadCredentialsException(messageUtil.getMessage("error.AE00003", userDto.getLang()));
        }
		
		return new UsernamePasswordAuthenticationToken(userDto, password, userDto.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
	    return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
