package com.capstone.proj.mp;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MPRowMapper implements RowMapper<MP> {

    @Override
    public MP mapRow(ResultSet rs, int rowNum) throws SQLException {
        MP mp = new MP(
                rs.getString("name"),
                rs.getInt("constituency_id"),
                rs.getString("email"),
                rs.getString("twitter"),
                rs.getString("photo_link")
        );
        return mp;
    }

}
