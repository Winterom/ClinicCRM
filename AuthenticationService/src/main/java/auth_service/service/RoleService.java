package auth_service.service;

import auth_service.entities.AppRole;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    AppRole getRoleById(Long id);
    AppRole getRoleByName(String name);
}
