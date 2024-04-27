package com.mx.sda.service.auth;

import java.security.Key;
import java.sql.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${security.jwt.expiration}")
	private Long minutosExpiracion;
	
	@Value("${security.jwt.secret-key}")
	private String secretKey;
	
	public String generateToken(UserDetails user, Map<String, Object> extraClaims) {
		
		Date fechaActual = new Date(System.currentTimeMillis());
		Date fechaExpiracion = new Date((minutosExpiracion * 60 * 100) + fechaActual.getTime());
		String jwt = Jwts.builder().setClaims(extraClaims).setSubject(user.getUsername()).setIssuedAt(fechaActual)
				.setExpiration(fechaExpiracion).setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.signWith(generateKey(), SignatureAlgorithm.HS256).compact();
		
		return jwt;
	}
	/*
	private Key generateKey() {
		byte[] passwordDecoded = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(passwordDecoded);
	}*/
	
	private Key generateKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256); // Changed to use Keys method
    }
	
	public Claims extractAllClaims(String jwt) {
		return Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(jwt).getBody();
	}
	
	public String extractUsername(String jwt) {
		return extractAllClaims(jwt).getSubject();
	}
	
}
