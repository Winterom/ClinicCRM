package auth_service.exception;

public class PhoneNumberValidationException extends RuntimeException{
    public PhoneNumberValidationException() {
        super("PhoneNumber not valid");
    }
}
