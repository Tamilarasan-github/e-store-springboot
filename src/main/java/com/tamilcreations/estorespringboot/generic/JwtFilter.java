package com.tamilcreations.estorespringboot.generic;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

   

	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Extract the JWT token from the "Authorization" header
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7); // Skip "Bearer " prefix

            // Validate the JWT token (implement your validation logic)
            boolean isValidToken = JwtToken.validateToken(jwtToken);

            if (isValidToken) {
                // Proceed with the request
                filterChain.doFilter(request, response);
            } else {
                // Invalid token, send unauthorized response
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
            }
        } else {
            // No JWT token present, proceed with the request (add logic for specific paths if needed)
            filterChain.doFilter(request, response);
        }
    }

}

