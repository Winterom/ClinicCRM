package auth_service.dto.role_dto;

import auth_service.entities.AppAuthority;
import lombok.Getter;
import lombok.Setter;

import java.util.List;



public class AppRoleCreateDto  {
            @Setter
            @Getter
            private Long id;
            @Setter
            @Getter
            private String roleName;
            @Setter
            @Getter
            private String description;
            @Setter
            @Getter
            private Boolean isLocked;
            @Setter
            @Getter
            private List<AppAuthority> authorities;
        }

