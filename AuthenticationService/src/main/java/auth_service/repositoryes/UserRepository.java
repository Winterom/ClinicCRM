package auth_service.repositoryes;

import auth_service.entityes.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> getAppUserByEmailAndIsLockedFalse(String email);
    Optional<AppUser> getAppUsersByEmail(String email);
    Optional<AppUser> getAppUserByPhoneNumber(String phoneNumber);

}
