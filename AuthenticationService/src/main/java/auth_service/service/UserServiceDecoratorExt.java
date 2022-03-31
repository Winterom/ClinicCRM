package auth_service.service;

import auth_service.dto.AppUserDto;
import auth_service.dto.PaginationEntity;
import auth_service.entities.AppAuthoritiesEnum;
import auth_service.entities.AppUser;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


/*перегружаем только те методы которые требуют проверки на права доступа*/
@Service("UserServiceDecoration")
public class UserServiceDecoratorExt extends UserServiceDecorator{

    public UserServiceDecoratorExt(UserService userService) {
        super(userService);
    }

    @Override
    public AppUser registerUser(AppUserDto.Request.Create userDto, HttpServletRequest request) {
        if(!checkAuthority(AppAuthoritiesEnum.ADMIN_USER_WRITE,request)){
            throw new AccessDeniedException("Доступ запрещен!");
        }
        return super.registerUser(userDto, request);
    }

    @Override
    public Boolean checkEmail(String email, HttpServletRequest request) {
        if(!checkAuthority(AppAuthoritiesEnum.ADMIN_USER_WRITE,request)){
            throw new AccessDeniedException("Доступ запрещен!");
        }
        return super.checkEmail(email, request);
    }

    @Override
    public Boolean checkPhoneNumber(String phoneNumber, HttpServletRequest request) {
        if(!checkAuthority(AppAuthoritiesEnum.ADMIN_USER_WRITE,request)){
            throw new AccessDeniedException("Доступ запрещен!");
        }
        return super.checkPhoneNumber(phoneNumber, request);
    }

    @Override
    public PaginationEntity<AppUserDto.Response.userTable> getAllUser(Integer page, Integer itemInPage, String sortField, boolean directSort, String searchField, String searchValue, HttpServletRequest request) {
        if(!checkAuthority(AppAuthoritiesEnum.ADMIN_USER_READ,request)){
            throw new AccessDeniedException("Доступ запрещен!");
        }
        return super.getAllUser(page, itemInPage, sortField, directSort, searchField, searchValue, request);
    }

    private boolean checkAuthority(AppAuthoritiesEnum authority, HttpServletRequest request){
        String roles = request.getHeader("roles");
        System.out.println("Роли"+roles);
        return roles != null;
    }
}
