package com.spring.backend.Token;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtToken {
    
    private SecretKey generateTokne(){
        String SECRETKEY = "duw3281y757e5Q@$@#^Ts6w6e/^oskqakpodjt7=_ois/[q]";
        return Keys.hmacShaKeyFor(SECRETKEY.getBytes());
    }

    public String GenerateToken(String Name){
        return Jwts.builder()
                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .issuedAt(new Date())
                .subject(Name)
                .signWith(generateTokne())
                .compact();
    }

    public String RefreshToken(String Name){
        return Jwts.builder()
                .expiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
                .issuedAt(new Date())
                .subject(Name)
                .signWith(generateTokne())
                .compact();
    }

    public String extractName(String Token){
        return Jwts.parser()
                    .verifyWith(generateTokne())
                    .build()
                    .parseSignedClaims(Token)
                    .getPayload()
                    .getSubject();
    }

    public boolean checkValid(String Token, String name){
        String username = extractName(Token);
        return name.equals(username) && !checkExp(Token);
    }

    public boolean checkExp(String Token){
        Date date = Jwts.parser()
                        .verifyWith(generateTokne())
                        .build()
                        .parseSignedClaims(Token)
                        .getPayload()
                        .getExpiration();
        return date.before(new Date());
    }
}
