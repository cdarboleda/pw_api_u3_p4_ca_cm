package com.example.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtUtils {

	private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${app.jwt.secret}")
	private String jwtSecret;

	public boolean validateJwtToken(String token) {
		try {
			
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;

			
		}catch (ExpiredJwtException e) {
			LOG.error("Token expirado {}",e.getMessage());

		}catch (SignatureException e) {
			LOG.error("Token invalido {}",e.getMessage());

		}
		
		return false;

	}
	
	//Nos da el nombre (subject) a partir del token
	public String getUsernameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
}
