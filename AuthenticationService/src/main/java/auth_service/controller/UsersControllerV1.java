package auth_service.controller;

import auth_service.dto.AppUserDto;
import auth_service.dto.PaginationEntity;
import auth_service.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/v1")
@Tag(name = "контроллер AppUser", description = "Контроллер предоставляющий информацию по Пользователям AppUser")
public class UsersControllerV1 {
    private final UserService userService;

    public UsersControllerV1(UserService userService) {
        this.userService = userService;
    }
    /*@PreAuthorize("hasAuthority(T(auth_service.entities.AppAuthoritiesEnum).ADMIN_USER_WRITE)")*/
    @PostMapping("/registration")
    public void registerAppUser(@RequestBody AppUserDto.Request.CreateOrUpdate userDto, HttpServletRequest request){
        userService.registerUser(userDto,request);
    }

    @PostMapping("/update")
    public void updateAppUser(@RequestBody AppUserDto.Request.CreateOrUpdate userDto, HttpServletRequest request){

    }


    /*@PreAuthorize("hasAnyAuthority(T(auth_service.entities.AppAuthoritiesEnum).ADMIN_USER_WRITE.name()" +
            ",T(auth_service.entities.AppAuthoritiesEnum).ADMIN_USER_READ.name())")*/
    @GetMapping("/get_all")
    public PaginationEntity<AppUserDto.Response.userTable> getAllUserInfo(
            @Parameter(description = "Номер текущей страницы") @RequestParam(name = "page", defaultValue = "1") Integer page,
            @Parameter(description = "Количество на странице")@RequestParam(name = "size", defaultValue = "10") Integer itemInPage,
            @Parameter(description = "Поле по которому происходит сортировка")@RequestParam(name = "sort_field", required = false) String sortField,
            @Parameter(description = "Сортировка по возрастанию true, иначе false")@RequestParam(name = "directSort", defaultValue = "false") boolean directSort,
            @Parameter(description = "Поле по которому происходит поиск")@RequestParam(name = "search_field", required = false) String searchField,
            @Parameter(description = "Искомое значение")@RequestParam(name = "search_value", required = false) String searchValue,
        HttpServletRequest request){
        return userService.getAllUser(page,itemInPage,sortField, directSort,searchField,searchValue,request);
    }
}
