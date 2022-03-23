package auth_service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

public enum RolesDto {;
    private interface Id { @Positive Long getId(); }
    private interface RoleName { @Size(min = 3, max = 50) String getRoleName(); }
    private interface Description {@NotBlank String getDescription();}
    private interface IsLocked {Boolean isLocked(); }
    private interface UserId {@Positive Long getUserId();}
    private interface AppAuthority{List<AppAuthority> getAuthorities();}

    public enum Request{;
        public static class Create {

        }
    }
}
