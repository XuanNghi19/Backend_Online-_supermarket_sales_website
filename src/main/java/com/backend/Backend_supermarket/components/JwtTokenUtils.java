package com.backend.Backend_supermarket.components;

import org.springframework.stereotype.Component;
import com.backend.Backend_supermarket.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils {

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secretKey}")
    private String secretKey;

    public String generateToken(User user){
        // tạo vùng chứa các claims
        Map<String, String> claims = new HashMap<>();
        // thêm số điện thoại vào claims
        claims.put("phoneNumber", user.getPhoneNumber());

        try {
            return Jwts.builder()
                .claims(claims)
                .subject(user.getPhoneNumber())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ expiration*1000L))
                .signWith(getSignInKey())
                .compact();

        } catch (Exception e) {
            // TODO: handle exception
            throw new InvalidParameterException("Cannot create jwt token !, error : " + e.getMessage());
        }

    }

    private SecretKey getSignInKey() {
        byte [] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenExpired(String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String extractPhoneNumber(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public boolean IsValidToken(String token, UserDetails userDetails){
        return isTokenExpired(token) && extractPhoneNumber(token).equals(userDetails.getUsername());
    }
}
