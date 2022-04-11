package auth_service.utils;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtils {

    @Setter @Getter
    @Value("${jwt.secret}")
    private String secret;

    @Setter @Getter
    @Value("${jwt.lifetime.access}")
    private Integer jwtAccessLifetime;

    @Setter @Getter
    @Value("${jwt.lifetime.refresh}")
    private Integer jwtRefreshLifetime;

    @Setter @Getter
    @Value("${jwt.refresh.length}")
    private Integer refreshTokenLength;


    public JwtTokenUtils() {
    }

    public String generateAccessToken(Collection< ? extends GrantedAuthority> authorities, String username) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        claims.put("roles", rolesList);
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtAccessLifetime);
        return Jwts.builder()
                .setClaims(claims)
                .claim("access", true)
                .setSubject(username)
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    public String generateRefreshToken() {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < refreshTokenLength; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }

}
