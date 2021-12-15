package com.capstone.proj.user;

import com.capstone.proj.constituency.Constituency;
import com.google.gson.Gson;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("password"),
                null,
                rs.getInt("constituency_id"),
                rs.getString("constituency_name")
        );
        return user;
    }
}
