package com.tamilcreations.estorespringboot.utils;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;

@Service
public class GenericService
{
		
	public String getAuthenticatedUserName() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        if (authentication != null && authentication.isAuthenticated()) {
            username = authentication.getName();
            //userService.loadUserByUsername(username); //here username is nothing but phone number
            
            System.out.println("Logged in username: " + username);
        } else {
            // Handle unauthenticated access
        	throw new Exception("Please login with valid credentials");
        }
        return username;
    }
	
	public String getAuthenticatedRole() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        StringBuilder rolesStringBuilder = new StringBuilder();
        
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            for (GrantedAuthority authority : authorities) {
                if (rolesStringBuilder.length() > 0) {
                    rolesStringBuilder.append(", ");
                }
                rolesStringBuilder.append(authority.getAuthority());
            }

            System.out.println("Roles: " + rolesStringBuilder.toString());
        } else {
            // Handle unauthenticated access
        	throw new Exception("Please login with valid credentials");
        }
        return rolesStringBuilder.toString();
    }
	
	public Claims getClaims() {
        // Get the authentication object from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Retrieve the claims from the authentication object
            Object principal = authentication.getPrincipal();

            if (principal instanceof Claims) {
                Claims claims = (Claims) principal;

                // Now you can access individual claims
                String username = claims.get("username", String.class);
                List<String> role = claims.get("role", List.class);

                System.out.println("Username: " + username);
                System.out.println("Role: " + role);
                
                return claims;
                
                // You can access other claims as needed
            } else {
            	throw new RuntimeException("No Claims found in the Authentication");
            }

        } else {
            // Handle unauthenticated access
        	throw new RuntimeException("Unauthenticated Access");
        }
    }
}
