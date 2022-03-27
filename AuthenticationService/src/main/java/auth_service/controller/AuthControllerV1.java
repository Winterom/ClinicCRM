package auth_service.controller;

import auth_service.dto.AppUserDto;
import auth_service.dto.JwtDto;
import auth_service.dto.TableAppUserDto;
import auth_service.dto.WrapperEntityDto;
import auth_service.entityes.AppAuthoritiesEnum;
import auth_service.entityes.AppUser;
import auth_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api/v1")
@Tag(name = "контроллер аутентификации", description = "Контроллер предоставляющий эндпоинты по сущности AppUser")
public class AuthControllerV1 {
    private final UserService userService;
    public AppAuthoritiesEnum authorities;

    public AuthControllerV1(UserService userService) {
        this.userService = userService;
    }
    @Operation(
            summary = "Запрос на аутентификацию пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = JwtDto.Response.class))
                    )
            }
    )
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtDto.Request authRequest) {
        return ResponseEntity.ok(userService.generateAccessToken(authRequest));
    }


    @PreAuthorize("hasAuthority(T(auth_service.entityes.AppAuthoritiesEnum).ADMIN_USER_WRITE)")
    @PostMapping("/registration")
    public AppUser registerAppUser(@RequestBody AppUserDto.Request.Create userDto){
        return userService.registerUser(userDto);
    }


    /*@PreAuthorize("hasAnyAuthority(T(auth_service.entityes.AppAuthoritiesEnum).ADMIN_USER_WRITE.name()" +
            ",T(auth_service.entityes.AppAuthoritiesEnum).ADMIN_USER_READ.name())")*/
    @GetMapping("/get_all")
    public ResponseEntity<WrapperEntityDto<TableAppUserDto>> getAllUserInfo(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "pi", defaultValue = "10") Integer itemInPage
    ){
        return userService.getAllUser(page);

    }
}
