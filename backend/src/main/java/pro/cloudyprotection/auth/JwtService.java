package pro.cloudyprotection.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import pro.cloudyprotection.user.User;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // ⚠ минимум 32 байта для HS256
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(
            "cloudyprotection-super-secret-key-2026!!".getBytes()
    );

    private static final long EXPIRATION_MS =
            1000L * 60 * 60 * 24 * 30; // 30 дней

    public String generateToken(User user) {
        return Jwts.builder()
                       .setSubject(user.getEmail())
                       .claim("uid", user.getId())
                       .setIssuedAt(new Date())
                       .setExpiration(
                               new Date(System.currentTimeMillis() + EXPIRATION_MS)
                       )
                       .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                       .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                       .setSigningKey(SECRET_KEY)
                       .build()
                       .parseClaimsJws(token)
                       .getBody();
    }
}
