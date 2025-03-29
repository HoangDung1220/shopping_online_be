package com.shoppingonline.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shoppingonline.common.BeanUtils;
import com.shoppingonline.security.CustomUserDetailsService;
import com.shoppingonline.security.UserDTO;
import com.shoppingonline.utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private static final String AUTHORIZATION = "Authorization";

	private JwtUtil jwtUtil;
	
	private CustomUserDetailsService userDetailService;
		
	public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailService) {
		this.jwtUtil = jwtUtil;
		this.userDetailService = userDetailService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		String token = null;
		String username = null;
		
		if (BeanUtils.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(token);
		}
		
		if (BeanUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null ) {
			UserDTO user = (UserDTO) userDetailService.loadUserByUsername(username);
			if (jwtUtil.validateToken(token, user)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                		user, null, user.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
