package auth_service.controller;

import auth_service.dto.AppUserDto;
import auth_service.dto.PaginationEntity;
import auth_service.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


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
    @PostMapping("/get_all")
    public PaginationEntity<AppUserDto.Response.userTable> getAllUserInfo(@Valid @RequestBody AppUserDto.Request.GetAllUserWithFilters reqParam,
        HttpServletRequest request){
        System.out.println(reqParam);

        System.out.println(reqParam.getStatus());
        return userService.getAllUser(reqParam.getPage(),reqParam.getItemInPage(),
                reqParam.getSortField(), reqParam.getDirectSort(), reqParam.getSearchField(),
                reqParam.getSearchValue(),reqParam.getStatus(),request);
    }
}
/* @Parameter(description = "Номер текущей страницы") @RequestBody(name = "page", defaultValue = "1") Integer page,
            @Parameter(description = "Количество на странице")@RequestParam(name = "size", defaultValue = "10") Integer itemInPage,
            @Parameter(description = "Поле по которому происходит сортировка")@RequestParam(name = "sort_field", required = false) String sortField,
            @Parameter(description = "Сортировка по возрастанию true, иначе false")@RequestParam(name = "directSort", defaultValue = "false") boolean directSort,
            @Parameter(description = "Поле по которому происходит поиск")@RequestParam(name = "search_field", required = false) String searchField,
            @Parameter(description = "Искомое значение")@RequestParam(name = "search_value", required = false) String searchValue,*/