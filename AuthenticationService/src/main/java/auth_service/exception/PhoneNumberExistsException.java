package auth_service.exception;

public class PhoneNumberExistsException extends RuntimeException{
    public PhoneNumberExistsException(String phoneNumber) {
        super("There is an account with that phone number "+phoneNumber);
    }
}
