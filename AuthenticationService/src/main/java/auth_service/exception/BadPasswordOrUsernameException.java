package auth_service.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class BadPasswordOrUsernameException extends BadCredentialsException {
    public BadPasswordOrUsernameException(){
        super("Incorrect username or password");
    }
}
