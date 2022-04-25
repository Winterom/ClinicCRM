package sender.command.services;


import org.springframework.stereotype.Service;
import sender.entity.VerifyToken;

import java.util.UUID;

@Service
public class VerifyTokenServiceImpl implements VerifyTokenService{
    private final IdentityVerifyToken identityVerifyToken;

    public VerifyTokenServiceImpl(IdentityVerifyToken identityVerifyToken) {
        this.identityVerifyToken = identityVerifyToken;
    }

    @Override
    public void saveToken(Long user_id) {
        String token = UUID.nameUUIDFromBytes(user_id.toString().getBytes()).toString();
        identityVerifyToken.saveToken(user_id,token);
    }

    @Override
    public VerifyToken getToken(Long user_id) {
        return identityVerifyToken.getVerifyToken(user_id);
    }
}
