package com.graduate.touslestemp.security.jwt;

import com.graduate.touslestemp.domain.entity.Role;
import com.graduate.touslestemp.service.impl.LocalUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @File: TokenAuthenticationFilter.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:25 AM
 * @Update: 21/6/2023
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private LocalUserDetailService customUserDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);
    /**
     * Filters the incoming request and processes the authentication based on the JWT token.
     * If a valid JWT token is present, it sets the user authentication in the security context.
     *
     * @param request      the HTTP servlet request
     * @param response     the HTTP servlet response
     * @param filterChain  the filter chain to proceed with the request processing
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs during the request processing
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            // Check and validate the JWT if it has a value and is valid
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                Long userId = tokenProvider.getUserIdFromToken(jwt);
                // Load user details from customUserDetailsService
                UserDetails userDetails = customUserDetailsService.loadUserById(userId);
                // Get authorities based on token authentication status
                Collection<? extends GrantedAuthority> authorities = tokenProvider.isAuthenticated(jwt)
                        ? userDetails.getAuthorities()
                        : List.of(new SimpleGrantedAuthority(Role.ROLE_PRE_VERIFICATION_USER));
                // Create an authentication token with user details, null credentials, and authorities
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Set the authentication in the security context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }
    /**
     * Extracts the JWT token from the Authorization header of the HttpServletRequest.
     *
     * @param request the HTTP servlet request
     * @return the extracted JWT token or null if it is not found
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}