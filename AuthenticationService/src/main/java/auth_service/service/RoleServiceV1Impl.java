package auth_service.service;

import auth_service.entities.AppRole;
import auth_service.exception.AppRoleNotFoundException;
import auth_service.repositoryes.RoleRepository;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceV1Impl implements RoleService{
    private final RoleRepository roleRepository;

    public RoleServiceV1Impl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public AppRole getRoleById(Long id) {
        return roleRepository.getAppRoleById(id).orElseThrow(()->new AppRoleNotFoundException(null,id));
    }

    @Override
    public AppRole getRoleByName(String name) {
        return roleRepository.getAppRoleByRoleName(name).orElseThrow(()->new AppRoleNotFoundException(name,null));
    }
}
