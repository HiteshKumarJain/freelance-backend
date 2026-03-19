//package com.myapp.freelance_backend.security;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//
//
////This class does Generate token, validate token, extract email from token
//@Component
//public class JwtUtil {
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private long expiration;
//
//    private SecretKey signingKey() {
//      return Keys.hmacShaKeyFor(secret.getBytes());
//    }
//
//    public String generateToken(String email){
//
//        String token = Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expiration))
//                .signWith(signingKey())
//                .compact();
//        return token;
//    }
//
//    public String extractEmail(String token) {
//       String email = Jwts.parserBuilder()
//                .setSigningKey(signingKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody().getSubject();
//       return email;
//    }
//
//    public boolean isTokenValid(String token) {
//        try{
//            String extractedEmail = extractEmail(token);
//            return extractedEmail != null;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//}
