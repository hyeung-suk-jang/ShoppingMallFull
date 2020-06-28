package com.example.parayo.global.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;

import java.util.Date;

public class JWTUtils2 {

    private static final String ISSUER = "Parayo";
    private static final String SUBJECT = "Auth";
    private static final Long EXPIRE_TIME = 60L * 60 * 2 * 1000;
    private static final Long REPRESH_EXPIRE_TIME = 60L * 60 * 24 * 30 * 1000;

    private static final String SECRET = "your-secret";
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET);

    private static final String REFRESH_SECRET = "your-refresh-secret";
    private static final Algorithm refreshAlgorithm = Algorithm.HMAC256(REFRESH_SECRET);

    private static final String EMAIL = "email";

    public String createToken(final String email) {
        Date now = new Date();
        JWTCreator.Builder builder = com.auth0.jwt.JWT.create();

        builder.withIssuer(ISSUER);
        builder.withSubject(SUBJECT);
        builder.withIssuedAt(now);
        builder.withExpiresAt(new Date(now.getTime() + EXPIRE_TIME));
        builder.withClaim(EMAIL, email);
        return builder.sign(algorithm);
    }

    public String createRefreshToken(final String email) {
        Date now = new Date();
        JWTCreator.Builder builder = com.auth0.jwt.JWT.create();

        builder.withIssuer(ISSUER);
        builder.withSubject(SUBJECT);
        builder.withIssuedAt(now);
        builder.withExpiresAt(new Date(now.getTime() + EXPIRE_TIME));
        builder.withClaim(EMAIL, email);
        return builder.sign(refreshAlgorithm);
    }

    public DecodedJWT verify(final String token) {
        return JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token);
    }

    public DecodedJWT verifyRefresh(final String token) {
        return JWT.require(refreshAlgorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token);
    }

    public String extractEmail(final DecodedJWT jwt) {
        return jwt.getClaim(EMAIL).asString();
    }

}
