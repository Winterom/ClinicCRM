package sender.command.services;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import sender.entity.VerifyToken;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class IdentityVerifyToken {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final Map<Long, VerifyToken> tokenMap;/*key = user_id*/
    private final Integer MAX_SIZE_FOR_CACHE = 250;

    public IdentityVerifyToken(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.tokenMap= new ConcurrentHashMap<>(MAX_SIZE_FOR_CACHE);
    }

    public boolean saveToken(Long user_id, String token){
        VerifyToken verifyToken = new VerifyToken();
        verifyToken.setToken(token);
        verifyToken.setUser_id(user_id);
        verifyToken.setExpired(LocalDateTime.now().plusHours(24));
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(verifyToken);

        int result = jdbcTemplate.update("INSERT into token values (?,?,?), user_id=:user_id,token=:token,expired=:expired",namedParameters);
        if(result!=1){
            return false;
        }
        if(tokenMap.size()==MAX_SIZE_FOR_CACHE){
            Optional<VerifyToken> lastToken = tokenMap.values().stream().min(Comparator.comparing(VerifyToken::getExpired));
            tokenMap.remove(lastToken.get().getUser_id());
        }
        tokenMap.put(user_id,verifyToken);
        return true;
    }

    public VerifyToken getVerifyToken(Long user_id){
        VerifyToken token = tokenMap.get(user_id);
        if(token!=null){
            return token;
        }
        String query = "SELECT * FROM token WHERE user_id = ?";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("user_id", user_id);
        token = jdbcTemplate.queryForObject(query,namedParameters,new VerifyTokenMapper());
        return token;
    }
}
