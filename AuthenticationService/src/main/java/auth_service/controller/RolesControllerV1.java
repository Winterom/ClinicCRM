package auth_service.controller;

import auth_service.dto.role_dto.AppRoleForUserDto;
import auth_service.entities.AppAuthoritiesEnum;
import auth_service.service.RoleService;
import auth_service.utils.RequestHeaderRoleChecker;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@Tag(name = "контроллер ролей", description = "Контроллер предоставляющий информацию по ролям и привелегиям")
public class RolesControllerV1 {
    private final RoleService roleService;

    public RolesControllerV1(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/user_roles/{userId}")
    public List<AppRoleForUserDto> getRolesForUser(@PathVariable Long userId, HttpServletRequest request){
        RequestHeaderRoleChecker.hasAuthority(AppAuthoritiesEnum.ADMIN_USER_READ,request);
        return roleService.getRolesForUser(userId);
    }
}
