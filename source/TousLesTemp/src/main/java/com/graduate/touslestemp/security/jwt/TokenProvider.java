package com.graduate.touslestemp.security.jwt;

import com.graduate.touslestemp.config.AppProperties;
import com.graduate.touslestemp.constant.SecurityConstant;
import com.graduate.touslestemp.domain.dto.LocalUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @File: TokenProvider.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:24 AM
 * @Update: 21/6/2023
 */
@Service
public class TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private AppProperties appProperties;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    /**
     * Creates a JWT token for the given user principal with the specified authentication status.
     *
     * @param userPrincipal the user principal
     * @param authenticated the authentication status
     * @return the created JWT token
     */
    public String createToken(LocalUser userPrincipal, boolean authenticated) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + (authenticated ? appProperties.getAuth().getTokenExpirationMsec() : SecurityConstant.TEMP_TOKEN_VALIDITY_IN_MILLIS));

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getUser().getId()))
                .claim(SecurityConstant.AUTHENTICATED, authenticated)
                .setAudience(userPrincipal.getUser().getRoles().toString())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    /**
     * Retrieves the user ID from the provided JWT token.
     *
     * @param token the JWT token
     * @return the user ID extracted from the token
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    /**
     * Checks if the provided JWT token represents an authenticated user.
     *
     * @param token the JWT token
     * @return true if the token represents an authenticated user, false otherwise
     */
    public Boolean isAuthenticated(String token) {
        Claims claims = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token).getBody();
        return claims.get(SecurityConstant.AUTHENTICATED, Boolean.class);
    }

    /**
     * Validates the provided JWT token.
     *
     * @param authToken the JWT token to validate
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}