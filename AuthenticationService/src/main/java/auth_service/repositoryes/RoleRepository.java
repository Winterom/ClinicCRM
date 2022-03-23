package auth_service.repositoryes;

import auth_service.entityes.AppRole;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface RoleRepository extends CrudRepository<AppRole,Long> {
    Optional<AppRole> getAppRoleById(Long id);
    Optional<AppRole> getAppRoleByRoleName(String name);
}
