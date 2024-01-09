package com.tamilcreations.estorespringboot.generic;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

public class JwtToken {

    private static final String SECRET_KEY = "9629006706";
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds
    private static final SecretKey key = Jwts.SIG.HS256.key().build();
    
    public static String generateToken(String subject) {
    	
    	
        return Jwts.builder()
                .subject(subject)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }
    
    public static boolean validateToken(String jwtToken) {
    	 try {
    	     Jwts.parser().verifyWith(key).build().parseSignedClaims(jwtToken).getPayload().getSubject();
             return true;
         } catch (SignatureException | MalformedJwtException e) {
             // Token signature or format is invalid
             return false;
         }
    }
    
    public static String getSubjectFromToken(String jwtToken) {
    	Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwtToken).getPayload();
    	return claims.getSubject();
    	
    }
    
}
