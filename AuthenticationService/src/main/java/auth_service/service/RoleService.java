package auth_service.service;


import auth_service.dto.role_dto.AppRoleForUserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<AppRoleForUserDto> getRolesForUser(Long userId);
}
