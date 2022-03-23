package auth_service.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime.access}")
    private Integer jwtAccessLifetime;

    @Value("${jwt.lifetime.refresh}")
    private Integer jwtRefreshLifetime;

    @Value("${jwt.refresh.length}")
    private Integer refreshTokenLength;


    public JwtTokenUtils() {
    }

    public String generateAccessToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream().map(Object::toString).collect(Collectors.toList());
        claims.put("roles", rolesList);
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtAccessLifetime);
        return Jwts.builder()
                .setClaims(claims)
                .claim("access",true)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public String generateRefreshToken(){
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < refreshTokenLength; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getJwtAccessLifetime() {
        return jwtAccessLifetime;
    }

    public void setJwtAccessLifetime(Integer jwtAccessLifetime) {
        this.jwtAccessLifetime = jwtAccessLifetime;
    }

    public Integer getJwtRefreshLifetime() {
        return jwtRefreshLifetime;
    }

    public void setJwtRefreshLifetime(Integer jwtRefreshLifetime) {
        this.jwtRefreshLifetime = jwtRefreshLifetime;
    }

    public Integer getRefreshTokenLength() {
        return refreshTokenLength;
    }

    public void setRefreshTokenLength(Integer refreshTokenLength) {
        this.refreshTokenLength = refreshTokenLength;
    }
}
