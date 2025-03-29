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
	
    private static final String SECRET_KEY = "nnhoangdung-dungnnh-200201-102190059-dut-dn-n4ha105c5JRWFnnhoangdung-dungnnh-200201-102190059-dut-dn-n4ha105c5JRWF@@"; 

    public static SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
    
	public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", username); 
		return Jwts.builder()
	                .claims(claims) 
	                .issuedAt(new Date()) 
	                .expiration(new Date(System.currentTimeMillis() + 172800000)) 
	                .signWith(getSigningKey())
	                .compact();
	}
	
	public String extractUsername(String token) {
		return extractClaim(token).get("email", String.class);
	}
	
    public boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token).getExpiration();
        return expiration.after(new Date());
    }
	
    public boolean validateToken(String token, UserDetails userDetails) {
    	return extractUsername(token).equals(userDetails.getUsername()) && isTokenExpired(token);
    }
    
	private Claims extractClaim(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())  
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims;        
	}

}
