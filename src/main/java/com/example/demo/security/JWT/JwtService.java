package com.example.demo.security.JWT;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final static String secretKey = "Secret"; //TODO: Store somewhere else and dont let that be the secret

    public Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

//    public boolean isNotExpired(String token){
//        try{
//            Date date = getClaims(token).getExpiration();
//            Date currentDate = new Date(System.currentTimeMillis());
//            return currentDate.before(date);
//        }catch(ExpiredJwtException e){
//            return false;
//        }
//    }

    public String getSubject(String token) throws JwtException {
      return getClaims(token).getSubject();
    }


}
