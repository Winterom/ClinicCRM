package auth_service.exception;

public class EmailExistsException extends RuntimeException{
    public EmailExistsException(String email) {
        super("There is an account with that email adress "+ email);
    }
}
