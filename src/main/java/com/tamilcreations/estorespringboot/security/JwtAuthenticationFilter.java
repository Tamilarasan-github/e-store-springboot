package com.tamilcreations.estorespringboot.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tamilcreations.estorespringboot.users.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private static final String SECRET_KEY = "9629006706";
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds
    private static final SecretKey key = Jwts.SIG.HS256.key().build();
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, UnsupportedJwtException, MalformedJwtException {
        try {
           
        	User user = getAuthorizationHeaderValueAndValidate(request);

                // Create Authentication object
               // Authentication authentication = new UsernamePasswordAuthenticationToken(user.getPhoneNumber(), null, user.getRole());

                // Set the authentication in the SecurityContext
               // SecurityContextHolder.getContext().setAuthentication(authentication);
            
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            // Handle token exceptions if needed
            // For example, you might want to redirect to a login page or return an error response
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
    
//    @Override
//    public int getOrder() {
//        // Set the order for your filter
//        return Ordered.HIGHEST_PRECEDENCE; // You can use any order value based on your requirements
//    }
    
    private String extractJwtToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
    
    public static String generateToken(String subject, String role, String uuid) {
    	 
    	
        String token =  Jwts.builder()
        		.claim("role", role)
        		.claim("uuid", uuid)
                .subject(subject)
               
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
        System.out.println("Generated Token:"+token);
        
       
        return token;
    }
    
    public static User validateToken(String token) throws Exception {
    	System.out.println("Token Received in Estore:"+token);
    	 try {
    		 
//    	        Jws<Claims> claims =  Jwts.parser()
//    	        		.verifyWith(key)
//    	                .build()
//    	                .parseSignedClaims(token);
    		 
    		// Split the token into parts
    	        String[] parts = token.split("\\.");

    	        // Decode each part
    	        String header = new String(Base64.getDecoder().decode(parts[0]), StandardCharsets.UTF_8);
    	        String payload = new String(Base64.getDecoder().decode(parts[1]), StandardCharsets.UTF_8);
    	        
    	        System.out.println("Payload:"+payload);
    	        
    	        ObjectMapper objectMapper = new ObjectMapper();
    	        JsonNode jsonNode = objectMapper.readTree(payload);

    	     

    	        // Extract information from claims
    	        String phoneNumber = jsonNode.get("sub").asText();
    	        String role = jsonNode.get("role").asText();
    	        String uuid = jsonNode.get("uuid").asText();
    	        
    	        System.out.println("phoneNumber:"+phoneNumber);
    	        System.out.println("role:"+role);
    	        System.out.println("uuid:"+uuid);

    	     
    	     //String uuid = claims.getPayload().get("uuid").toString();
    	     //String phoneNumber = claims.getPayload().getSubject();
    	     //String role = claims.getPayload().get("role").toString();
    	    // String role = claims.getPayload().get("authorities").toString();
    	     
    	     List<GrantedAuthority> grandtedAuthorities = new ArrayList<>();
    	     grandtedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
    	    		 
             Authentication authentication = new UsernamePasswordAuthenticationToken(phoneNumber, null, grandtedAuthorities);
             SecurityContext context = SecurityContextHolder.createEmptyContext();
             context.setAuthentication(authentication);
             
             SecurityContextHolder.setContext(context); 
             //SecurityContextHolder.getContext().setAuthentication(authentication);
             
//             SecurityContext context = SecurityContextHolder.getContext();
//             Authentication authentication = context.getAuthentication();
//             String username = authentication.getName();
//             Object principal = authentication.getPrincipal();
//             Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    	     
    	     User user  = new User();
    	     user.setUuid(uuid);
    	     user.setPhoneNumber(phoneNumber);
    	     user.setRole(role);
    	     
             return user;
         } catch (SignatureException | MalformedJwtException e) {
             // Token signature or format is invalid
             throw new Exception("Invalid token "+e.getMessage());
         }
    }
    
    public static String getSubjectFromToken(String jwtToken) {
    	Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwtToken).getPayload();
    	return claims.getSubject();
    	
    }
    
    public static User getAuthorizationHeaderValueAndValidate(HttpServletRequest request) throws Exception
    {
    String authorizationHeader = request.getHeader("Authorization");
	 System.out.println("authorizationHeader: "+authorizationHeader);
       if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
           String jwtToken = authorizationHeader.substring(7); // Skip "Bearer " prefix

           User user = JwtAuthenticationFilter.validateToken(jwtToken);
           return user;
       }
       else
       {
       	throw new Exception("Please login to perform this action.");
       }
    }
}
