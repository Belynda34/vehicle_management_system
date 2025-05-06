package com.example.VehicleManagement.config;

import com.example.VehicleManagement.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    @Value("${spring.app.jwt-secret}")
    private String SECRET_KEY;


    public String extractEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }


    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extraAllClaims(token);
        return claimsResolver.apply(claims);
    }



    private Claims extraAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetails userDetails){
        String email =((User)userDetails).getEmail();
        String role = ((User)userDetails).getRole().name();
        Map<String,Object> claims = new HashMap<>();
        claims.put("email",email);
        claims.put("role",role);
        return generateTokenWithClaims(claims,userDetails);
    }

    private Key getSigningKey() {
        byte[] key = SECRET_KEY.getBytes();
//        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }
    public String generateTokenWithClaims(Map<String,Object>claims,UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(((User)userDetails).getEmail())//put in email for tokens
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        try{
            final String email = extractClaim(token,claims -> claims.get("email",String.class));
            return (email.equals(((User)userDetails).getEmail()) && !isTokenExpired(token));
        }catch (Exception e ){
            System.err.println("Error during token validation: " + e.getMessage());
            return false;
        }

    }

    public boolean isTokenExpired(String token){
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }

}



//    public String generateToken(Map<String,Object>claims,UserDetails userDetails){
//        return Jwts
//                .builder()
//                .setClaims(claims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }


//    public boolean isTokenValid(String token,UserDetails userDetails){
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
//    }
