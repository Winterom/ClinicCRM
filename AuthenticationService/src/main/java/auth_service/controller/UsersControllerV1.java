package auth_service.controller;

import auth_service.dto.PaginationEntity;
import auth_service.dto.user_dto.AppUserDto;
import auth_service.entities.AppAuthoritiesEnum;
import auth_service.service.UserService;
import auth_service.utils.RequestHeaderRoleChecker;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "контроллер AppUser", description = "Контроллер предоставляющий информацию по Пользователям AppUser")
public class UsersControllerV1 {
    private final UserService userService;

    public UsersControllerV1(UserService userService) {
        this.userService = userService;
    }
    /*@PreAuthorize("hasAuthority(T(auth_service.entities.AppAuthoritiesEnum).ADMIN_USER_WRITE)")*/

    @PostMapping("/registration")
    public void registerAppUser(@RequestBody AppUserDto.Request.CreateOrUpdate userDto, HttpServletRequest request){
        RequestHeaderRoleChecker.hasAuthority(AppAuthoritiesEnum.ADMIN_USER_WRITE,request);
        userService.registerUser(userDto);
    }

    @PostMapping("/update")
    public void updateAppUser(@RequestBody AppUserDto.Request.CreateOrUpdate userDto, HttpServletRequest request){
        RequestHeaderRoleChecker.hasAuthority(AppAuthoritiesEnum.ADMIN_USER_WRITE,request);
    }

    @PostMapping("/get_all")
    public PaginationEntity<AppUserDto.Response.UserTable> getAllUserInfo(@Validated @RequestBody AppUserDto.Request.GetAllUserWithFilters reqParam,
                                                                          HttpServletRequest request){
        RequestHeaderRoleChecker.hasAuthority(AppAuthoritiesEnum.ADMIN_USER_READ,request);
        return userService.getAllUser(reqParam.getPage(),reqParam.getItemInPage(),
                reqParam.getSortField(), reqParam.getDirectSort(), reqParam.getSearchField(),
                reqParam.getSearchValue(),reqParam.getStatus());
    }

    @GetMapping("/get_by_id/{id}")
    public AppUserDto.Response.UserProfile getUserProfileById(@PathVariable Long id, HttpServletRequest request){
        RequestHeaderRoleChecker.hasAuthority(AppAuthoritiesEnum.ADMIN_USER_READ,request);
        return userService.getUserById(id);
    }
}
