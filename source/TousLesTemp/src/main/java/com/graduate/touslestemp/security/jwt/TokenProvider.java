package com.graduate.touslestemp.security.jwt;

import com.graduate.touslestemp.config.AppProperties;
import com.graduate.touslestemp.constant.SecurityConstant;
import com.graduate.touslestemp.domain.dto.LocalUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;


@Service
public class TokenProvider {
	private String SECRET_KEY = SecurityConstant.SECRET;

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

		return Jwts.builder().setSubject(Long.toString(userPrincipal.getUser().getId())).claim(AUTHENTICATED, authenticated).setIssuedAt(new Date()).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();

		return Long.parseLong(claims.getSubject());
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	public Boolean isAuthenticated(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
		return claims.get(AUTHENTICATED, Boolean.class);
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(authToken);
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