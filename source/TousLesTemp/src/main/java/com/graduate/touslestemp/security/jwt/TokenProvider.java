package com.graduate.touslestemp.security.jwt;

import com.graduate.touslestemp.config.AppProperties;
import com.graduate.touslestemp.domain.dto.LocalUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
/*
* @File:  TokenProvider.java com.graduate.touslestemp.security.jwt
*
* @Author: TamNLT
* @Since: 20/6/2023 11:29 PM
* @Last update: 20/6/2023
*
* */
@Service
public class TokenProvider {

    private static final String AUTHENTICATED = "authenticated";

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    public static final long TEMP_TOKEN_VALIDITY_IN_MILLIS = 300000;

    private AppProperties appProperties;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String createToken(LocalUser userPrincipal, boolean authenticated) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + (authenticated ? appProperties.getAuth().getTokenExpirationMsec() : TEMP_TOKEN_VALIDITY_IN_MILLIS));

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getUser().getId()))
                .claim(AUTHENTICATED, authenticated)
                .setAudience(userPrincipal.getUser().getRoles().toString())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }


    public Boolean isAuthenticated(String token) {
        Claims claims = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token).getBody();
        return claims.get(AUTHENTICATED, Boolean.class);
    }

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