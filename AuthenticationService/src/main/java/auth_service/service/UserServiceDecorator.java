package auth_service.service;

import auth_service.dto.AppUserDto;
import auth_service.dto.JwtDto;
import auth_service.dto.PaginationEntity;
import auth_service.entities.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;

/*Ввиду того что реализована схема авторизации через JWT токен. Проверка токена осуществляется в GatewayService
*При обращении авторизованного пользователя прошедшего проверку в GatewayService, в SecurityContext пусто, ни прав на Principal
* задача класса декоратора расширить возможности на предмет проверки прав на предоставлении доступа к запросам */

public class UserServiceDecorator implements UserService{
    private UserService userService;

    public UserServiceDecorator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public JwtDto.Response generateAccessToken(JwtDto.Request authRequest) {
        return userService.generateAccessToken(authRequest);
    }

    @Override
    public AppUser registerUser(AppUserDto.Request.Create userDto, HttpServletRequest request) {
        return userService.registerUser(userDto,request);
    }

    @Override
    public Boolean checkEmail(String email, HttpServletRequest request) {
        return userService.checkEmail(email,request);
    }

    @Override
    public Boolean checkPhoneNumber(String phoneNumber, HttpServletRequest request) {
        return userService.checkPhoneNumber(phoneNumber,request);
    }

    @Override
    public PaginationEntity<AppUserDto.Response.userTable> getAllUser(Integer page, Integer itemInPage, String sortField, boolean directSort, String searchField, String searchValue, HttpServletRequest request) {
        return userService.getAllUser(page,itemInPage,sortField,directSort,searchField,searchValue,request);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userService.loadUserByUsername(s);
    }
}
