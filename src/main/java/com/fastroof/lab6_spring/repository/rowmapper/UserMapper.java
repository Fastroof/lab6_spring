package com.fastroof.lab6_spring.repository.rowmapper;

import com.fastroof.lab6_spring.entity.User;
import com.fastroof.lab6_spring.enums.Provider;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setFullName(rs.getString("full_name"));
        user.setProvider(Provider.valueOf(rs.getString("provider")));
        return user;
    }
}
