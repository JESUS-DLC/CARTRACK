package dev.jesusdlc.cartrack.config.security;

import dev.jesusdlc.cartrack.business.exception.JwtVerificationException;
import dev.jesusdlc.cartrack.domain.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.time}")
    private Long EXPIRATION_TIME;

    @Value("${security.jwt.key}")
    private String SECRET_KEY;

    public String generateToken(Usuario user){
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date((EXPIRATION_TIME * 60 *1000) + issuedAt.getTime());
        Map<String,Object> claims = generateClaims(user);
        String jwt = Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .claims(claims)
                .expiration(expiration)
                .signWith(generateKey(),Jwts.SIG.HS256)
                .compact();

        return jwt;
    }


    public Map<String, Object> generateClaims(Usuario user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().getName());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }

    private SecretKey generateKey(){
        byte[] decodeKey = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decodeKey);
    }

    public String extractEmail(String jwt){
        return extractAllClaims(jwt).getSubject();
    }

    private Claims extractAllClaims(String  jwt){
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }


}
