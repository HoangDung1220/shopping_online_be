package com.shoppingonline.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.shoppingonline.utils.MessageUtil;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageUtil messageUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(messageUtil.getMessage("error.AE00001", null)));
		Set<SimpleGrantedAuthority> authorities = user.getAuthorities().stream()
													   .map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toSet());
		
		return new UserDTO(user.getEmail(), user.getPassword(), user.getLang(), user.isEnabled(), authorities);
	}

}
