package com.graduate.touslestemp.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * @File: RestAuthenticationEntryPoint.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:25 AM
 * @Update: 21/6/2023
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    /**
     * Invoked when an unauthenticated user tries to access a secured resource.
     * It handles the unauthorized error by logging the error message and sending an HTTP_UNAUTHORIZED (401) response to the client.
     *
     * @param httpServletRequest  the HTTP servlet request
     * @param httpServletResponse the HTTP servlet response
     * @param e                   the AuthenticationException representing the unauthorized error
     * @throws IOException if an I/O error occurs during the response handling
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        logger.error("Unauthorized error. Message - {}", e.getMessage());
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getLocalizedMessage());
    }
}