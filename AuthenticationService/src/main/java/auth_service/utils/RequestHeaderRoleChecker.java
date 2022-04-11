package auth_service.utils;

import auth_service.entities.AppAuthoritiesEnum;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class RequestHeaderRoleChecker {
    public static void hasAuthority(AppAuthoritiesEnum authorities, HttpServletRequest request){
        String roles = request.getHeader("roles");
        if(roles != null){
            List<String> rolesList = Arrays.stream(roles.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",")).toList();
            if(rolesList.contains(authorities.name())){
                return;
            }
        }
        throw new AccessDeniedException("Доступ запрещен!");
    }
}
