package sender.command.services;


import sender.entity.VerifyToken;

public interface VerifyTokenService {
    void saveToken(Long user_id);
    VerifyToken getToken(Long user_id);
}
