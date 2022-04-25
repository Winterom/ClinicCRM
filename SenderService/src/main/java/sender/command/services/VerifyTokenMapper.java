package sender.command.services;


import org.springframework.jdbc.core.RowMapper;
import sender.entity.VerifyToken;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class VerifyTokenMapper implements RowMapper<VerifyToken> {
    @Override
    public VerifyToken mapRow(ResultSet resultSet, int row) throws SQLException {
        VerifyToken token = new VerifyToken();
        token.setId(resultSet.getLong("id"));
        token.setToken(resultSet.getString("token"));
        token.setUser_id(resultSet.getLong("user_id"));
        token.setExpired(resultSet.getObject("expired", LocalDateTime.class));
        return token;
    }
}
