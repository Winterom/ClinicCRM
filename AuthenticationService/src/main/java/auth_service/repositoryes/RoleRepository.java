package auth_service.repositoryes;

import auth_service.entities.AppRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface RoleRepository extends CrudRepository<AppRole,Long> {

}
