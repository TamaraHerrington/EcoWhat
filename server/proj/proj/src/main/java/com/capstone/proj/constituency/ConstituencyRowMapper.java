package com.capstone.proj.constituency;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConstituencyRowMapper implements RowMapper<Constituency> {

    @Override
    public Constituency mapRow(ResultSet rs, int rowNum) throws SQLException {
        Constituency constituency = new Constituency(
                rs.getInt("constituency_id"),
                rs.getString("constituency_name")
        );
        return constituency;
    }
}
