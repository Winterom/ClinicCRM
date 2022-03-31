package auth_service.exception;

public class RefreshTokenExpiredOrNotFoundException extends RuntimeException{
    public RefreshTokenExpiredOrNotFoundException() {
        super("Refresh token expired or not found");
    }
}
