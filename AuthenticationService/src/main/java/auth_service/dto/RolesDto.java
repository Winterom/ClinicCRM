package auth_service.dto;

import auth_service.entities.AppAuthority;
import auth_service.entities.AppUser;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

public enum RolesDto {;
    private interface Id { @Positive Long getId(); }
    private interface RoleName { @Size(min = 3, max = 50) String getRoleName(); }
    private interface Description {@NotBlank String getDescription();}
    private interface IsLocked {Boolean isLocked(); }
    private interface Users {@Positive List<Long> getUsers();}
    private interface Authorities{List<AppAuthority> getAuthorities();}

    public enum Request{;
        public static class Create implements Id,RoleName,Description,IsLocked,Users,Authorities{
            private Long id;
            private String roleName;
            private String description;
            private Boolean isLocked;
            private List<Long> appUser;
            private List<AppAuthority> authorities;

            public void setId(Long id) {
                this.id = id;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public void setLocked(Boolean locked) {
                isLocked = locked;
            }

            public void setAppUser(List<Long> appUser) {
                this.appUser = appUser;
            }

            public void setAuthorities(List<AppAuthority> authorities) {
                this.authorities = authorities;
            }

            @Override
            public Long getId() {
                return this.id;
            }

            @Override
            public String getRoleName() {
                return this.roleName;
            }

            @Override
            public String getDescription() {
                return this.description;
            }

            @Override
            public Boolean isLocked() {
                return this.isLocked;
            }


            @Override
            public List<Long> getUsers() {
                return this.appUser;
            }

            @Override
            public List<AppAuthority> getAuthorities() {
               return this.authorities;
            }

        }
    }
}
