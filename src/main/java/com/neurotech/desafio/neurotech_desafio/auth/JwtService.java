package com.neurotech.desafio.neurotech_desafio.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration-ms}")
  private long expirationMs;

  public String generateToken(String username) {
    Date now = new Date();
    Date exp = new Date(now.getTime() + expirationMs);

    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(now)
        .setExpiration(exp)
        .signWith(SignatureAlgorithm.HS256, secret.getBytes())
        .compact();
  }

  public String getUsername(String token) {
    return Jwts.parser()
        .setSigningKey(secret.getBytes())
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public boolean isValid(String token) {
    try {
      Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }
}
