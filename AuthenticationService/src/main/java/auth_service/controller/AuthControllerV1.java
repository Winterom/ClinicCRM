package auth_service.controller;

import auth_service.dto.jwt_dto.JwtDto;
import auth_service.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "контроллер аутентификации", description = "Контроллер предоставляющий эндпоинт аутентификации")
public class AuthControllerV1 {
    private final AuthService authService;

    public AuthControllerV1(AuthService authService) {
        this.authService = authService;
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
        return ResponseEntity.ok(authService.generateAccessToken(authRequest));
    }
}
