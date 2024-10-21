package com.example.demo.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

	@Value("${security.jwt.token.expire-length}")
	private long validityInMilliseconds;

	@Value("${seed.jwt.secret.login}")
	private String secretKey;

	public JwtUtil(){}

	public String generateToken(String user) {

		Map<String, Object> claims = new HashMap<>();
		claims.put("email", user);
		claims.put("role", "USER");

		Date now = new Date();
		Date expiration = new Date(now.getTime() + validityInMilliseconds);
		return Jwts.builder()
				.claims(claims)
				.issuedAt(now)
				.expiration(expiration)
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
				.compact();
	}

	public boolean validateToken(String token){
		try{
			String email = this.getClaimProperty(token, "email");
			if(email != null) {
				return true;
			}
		}catch(JwtException e) {}
		return false;
	}


	public String getClaimProperty(String token, String property){
		 return Jwts.parser().verifyWith(
						Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
				.build()
				.parseSignedClaims(token)
				.getPayload().get(property, String.class);
	}
}