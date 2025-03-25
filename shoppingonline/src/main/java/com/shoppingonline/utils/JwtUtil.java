package com.shoppingonline.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
    private static final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secretyour-256-bit-secret-your-256-bit-secretyour-256-bit-secret-your-256-bit-secret"; // Ít nhất 32 bytes
	
    public static SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
    
	public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", username); 
        
		return Jwts.builder()
	                .claims(claims) 
	                .issuedAt(new Date()) 
	                .expiration(new Date(System.currentTimeMillis() + 3600000)) 
	                .signWith(getSigningKey())
	                .compact();
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, "email");
	}
	
    public boolean isTokenExpired(String token) {
    	return true;
    }
	
    public boolean validateToken(String token, UserDetails userDetails) {
    	return extractUsername(token).equals(userDetails.getUsername()) && isTokenExpired(token);
    }
    
	private String extractClaim(String token, String claimKey) {

        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())  
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get(claimKey, String.class);
        
	}

}
