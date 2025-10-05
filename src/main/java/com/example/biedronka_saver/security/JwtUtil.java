package com.example.biedronka_saver.security;

import com.example.biedronka_saver.config.properties.TokenProperties;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@Log4j2
public class JwtUtil {
    private final TokenProperties tokenProperties;
    private final SecretKey secretKey;

    public JwtUtil(TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
        this.secretKey = Keys.hmacShaKeyFor(tokenProperties.signingKey().getBytes());
    }

    private SecretKey getSigningKey() {
        return secretKey;
    }

    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenProperties.expirationTimeSeconds().toMillis()))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.warn("JWT expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.warn("Unsupported JWT: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.warn("Malformed JWT: {}", e.getMessage());
        } catch (SecurityException | IllegalArgumentException e) {
            log.warn("Invalid JWT signature or empty token: {}", e.getMessage());
        }
        return false;
    }
}
