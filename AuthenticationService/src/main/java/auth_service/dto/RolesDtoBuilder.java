package auth_service.dto;

import auth_service.entities.AppAuthority;
import auth_service.entities.AppUser;

import java.util.List;
import java.util.stream.Collectors;

public class RolesDtoBuilder {
    private final RolesDto.Request.Create rolesCreate;

    public RolesDtoBuilder(RolesDto.Request.Create rolesTableItem) {
        this.rolesCreate = rolesTableItem;
    }

    public RolesDtoBuilder setName(String s){
        rolesCreate.setRoleName(s);
        return this;
    }
    public RolesDtoBuilder setDescription(String s){
        rolesCreate.setDescription(s);
        return this;
    }
    public RolesDtoBuilder setLocked(Boolean s){
        rolesCreate.setLocked(s);
        return this;
    }
    public RolesDtoBuilder setUsers(List<AppUser> users){
        rolesCreate.setAppUser(users.stream().map(AppUser::getId).collect(Collectors.toList()));
        return this;
    }
    public RolesDtoBuilder setAuthorities(List<AppAuthority> authorities){
        rolesCreate.setAuthorities(authorities);
        return this;
    }

}
