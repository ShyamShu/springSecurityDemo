package com.example.service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mongodb.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTservice {
	
	 String  secretKey  = "07fa16";
	public JWTservice() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
			SecretKey keyGenerator2 = keyGenerator.generateKey();
			secretKey = Base64.getEncoder().encodeToString(keyGenerator2.getEncoded());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	public String generateToken(String username) {
		
		Map<String , Object> claims = new HashMap<String, Object>();
		
		return Jwts.builder()
               .claims()
               .add(claims)
               .subject(username)
               .issuedAt(new Date(System.currentTimeMillis()))
               .expiration(new Date(System.currentTimeMillis()  + 60 *60 * 30))
               .and()
               .signWith(getKey())
               .compact();
               
		
	}

	
	private SecretKey getKey() {
		
		byte[] keynBytes = Decoders.BASE64.decode(secretKey) 	;
		return Keys.hmacShaKeyFor(keynBytes);
	}


	public String extractUserName(String token) {
		return extractClaim(token , Claims::getSubject);
	}


	private <T> T extractClaim(String token, Function<Claims, T> claimResolver ) {
		// TODO Auto-generated method stub
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}


	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser()
				.verifyWith(getKey())
				.build().parseSignedClaims(token).getPayload();
		
	}


	public boolean validate(String token, UserDetails userDetails) {
		// TODO Auto-generated method stub
		
		final String username = extractUserName(token);
		return (username.equals(userDetails.getUsername())&&  !isTokenExpired(token));
	}


	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}


	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getExpiration);
	}
}
