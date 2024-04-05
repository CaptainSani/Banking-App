package com.sani.World.Banking.App.infrastructure.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;



@Component
public class JwtTokenProvider {


    @Value("${app.jwt-secret")
    private  String jwtSecret;

    @Value("${app.jwt-expiration}")
    private Long jwtExpirationDate;

    //method to generate token

    public String generateToken(Authentication authentication){

        String username = authentication.getName();
        Date currentDate = new Date();
        Date  expirationDate = new Date(currentDate.getTime()+ jwtExpirationDate);


        return Jwts.builder()
                .subject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(key())
                .compact();
    }

    //this key helps  encrypt the token generated
    private Key key(){
        byte[] bytes = Decoders.BASE64.decode(jwtSecret);

        return Keys.hmacShaKeyFor(bytes);
    }

    //this method helps us get the name associated with the token

    public String getUserName(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key())
                .build()
                .parseClaimsJwt(token)
                .getPayload();

        return claims.getSubject();
    }


    //this method helps us validate that the token belongs to the right person

    public boolean validateToken(String  token){

        try{
            Jwts.parser()
                    .setSigningKey(key())
                    .build()
                    .parse(token);

            return true;
        } catch (ExpiredJwtException | IllegalArgumentException | SecurityException | MalformedJwtException e) {
            throw new RuntimeException(e);
        }
    }

}
