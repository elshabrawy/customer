package com.shabrawy.customer.util;

import com.shabrawy.customer.model.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Component
public class JwtUtil implements Serializable {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private static final long serialVersionUID = -2550185165626007488L;
    //    @Value("secret")
    private String secret = "0xA1B2C3D4E5F601A2B3C4D5E6F708090A0B1C2D3E4F5A6B7C8D9EAFB0C1D2E3F4";

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {

        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {

        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSignedKey())
                .parseClaimsJws(token)
                .getBody();
    }

    public Key getSignedKey(){
        byte[] keyBytes= Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(Customer userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(getSignedKey(),SignatureAlgorithm.HS256).compact();
    }

    //validate token
    public Boolean validateToken(String token, Optional<Customer> userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.get().getUsername()) && !isTokenExpired(token));
    }
}