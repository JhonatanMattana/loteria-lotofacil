package br.com.loteria.security.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class TokenService {
    private static final String SECRET_KEY = "minha-chave-super-secreta-com-pelo-menos-32-caracteres-para-funcionar";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    private static final long EXPIRATION_TIME = 86400000; // 24 horas

    public static final String gerarToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String validarToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

}
