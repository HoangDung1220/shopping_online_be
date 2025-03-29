package com.shoppingonline.api.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingonline.base.BaseController;
import com.shoppingonline.utils.JwtUtil;
import com.shoppingonline.utils.UserUtil;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController<AuthRequest, AuthResponse> {
	
    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;
    
    private PasswordEncoder passwordEncoder;
            
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, AuthService authService, UserUtil userUtil) {
    	super(authService, userUtil);
    	this.authenticationManager = authenticationManager;
    	this.jwtUtil = jwtUtil;
    	this.passwordEncoder = passwordEncoder;
    	this.setUser(userUtil.getUser());
    }

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest authRequest) {
		AuthResponse authResponse = new AuthResponse();
		if (service.prepare(authRequest,authResponse)) {
			authRequest.setPassword(passwordEncoder.encode(authRequest.getPassword())); 
			service.save(authRequest);	
			authResponse.setStatus(200);
		} else {
			authResponse.setStatus(400);
		}
        return ResponseEntity.ok(authResponse);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest authRequest) {
		AuthResponse authResponse = new AuthResponse();
		if (service.prepare(authRequest,authResponse)) {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
			authResponse.setToken(jwtUtil.generateToken(authRequest.getEmail()));
			authResponse.setStatus(200);
		} else {
			authResponse.setStatus(400);
		}
		return ResponseEntity.ok(authResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<AuthResponse> handleException(Exception e){
		AuthResponse response = new AuthResponse();
		response.setStatus(400);
		response.addErrorItems(new AuthErrorItem("exception", e.getMessage()));

		return ResponseEntity.ok(response);
	}
}
