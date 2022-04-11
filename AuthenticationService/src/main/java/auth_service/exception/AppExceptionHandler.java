package auth_service.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler
    public ResponseEntity<AppError> catchBadPasswordOrUsername(BadPasswordOrUsernameException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.name(), e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> notFoundRole(AppRoleNotFoundException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.name(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> notValidPhoneNumber(PhoneNumberValidationException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.UNPROCESSABLE_ENTITY.name(), e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler
    public ResponseEntity<AppError> phoneNumberExists(PhoneNumberExistsException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.CONFLICT.name(), e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> emailExist(EmailExistsException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.CONFLICT.name(), e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> refreshTokenNotValid(RefreshTokenExpiredOrNotFoundException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.name(), e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler
    public ResponseEntity<AppError> UserNotFoundException(AppUserNotFoundException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.name(), e.getMessage()), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> accessDeniedException(AccessDeniedException e){
        log.error(e.getMessage(),e);
        return new ResponseEntity<>("Access denied", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({IllegalArgumentException.class })
    public ResponseEntity<Object> badRequestException(IllegalArgumentException e){
        log.error(e.getMessage(),e);
        return new ResponseEntity<>("Не поддерживаемый запрос", new HttpHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

}
