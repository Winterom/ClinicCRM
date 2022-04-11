package auth_service.service;

import auth_service.dto.jwt_dto.JwtDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface AuthService extends UserDetailsService {
    JwtDto.Response generateAccessToken(JwtDto.Request authRequest);
}
