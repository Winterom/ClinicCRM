package auth_service.dto.role_dto;

import auth_service.entities.AppRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
public class AppRoleForUserDto {
    @Setter@Getter
    private Long id;
    @Setter@Getter
    private String roleName;
    @Setter@Getter
    private String description;
    @Setter@Getter
    private boolean userHasRole;

    public AppRoleForUserDto(AppRole role){
        this.id = role.getId();
        this.roleName = role.getRoleName();
        this.description = role.getDescription();
    }
}
