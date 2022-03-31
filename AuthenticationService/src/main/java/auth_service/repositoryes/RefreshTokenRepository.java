package auth_service.repositoryes;


import auth_service.entities.AppUser;
import auth_service.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> getRefreshTokenByToken(String token);
    void deleteRefreshTokenByAppUser(AppUser appUser);
}
