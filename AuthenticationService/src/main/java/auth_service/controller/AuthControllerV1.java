package auth_service.controller;

import auth_service.dto.AppUserDto;
import auth_service.dto.JwtDto;
import auth_service.dto.TableAppUser;
import auth_service.dto.WrapperEntityDto;
import auth_service.entityes.AppAuthoritiesEnum;
import auth_service.entityes.AppUser;
import auth_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1")
public class AuthControllerV1 {
    private final UserService userService;
    public AppAuthoritiesEnum authorities;

    public AuthControllerV1(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtDto.Request authRequest) {
        return ResponseEntity.ok(userService.generateAccessToken(authRequest));
    }

    @PreAuthorize("hasAuthority(T(auth_service.entityes.AppAuthoritiesEnum).ADMIN_USER_WRITE)")
    @PostMapping("/registration")
    public AppUser registerAppUser(@RequestBody AppUserDto.Request.Create userDto){
        return userService.registerUser(userDto);
    }


    @GetMapping("/get_all/{page}")
    public ResponseEntity<WrapperEntityDto<TableAppUser>> getAllUserInfo(@PathVariable Integer page){
        return userService.getAllUser(page);

    }
}
