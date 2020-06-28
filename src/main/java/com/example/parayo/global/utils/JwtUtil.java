package com.example.parayo.global.utils;

import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String ISSUER = "Parayo";
    private static final String SUBJECT = "Auth";
    private static final Long EXPIRE_TIME = 60L * 60 * 2 * 1000;
    private static final Long REPRESH_EXPIRE_TIME = 60L * 60 * 24 * 30 * 1000;

    private static final String SECRET = "your-secret";
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET);

    private static final String REFRESH_SECRET = "your-refresh-secret";
    private static final Algorithm refreshAlgorithm = Algorithm.HMAC256(REFRESH_SECRET);
    private static final String EMAIL = "email";

    private static Key key;

    public JwtUtil(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public static String createToken(String email) {
        Date now = new Date();

        String token = Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(SUBJECT)
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    public static String refreshToken(String email) {
        Date now = new Date();

        String token = Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(SUBJECT)
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REPRESH_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    public Claims getClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
