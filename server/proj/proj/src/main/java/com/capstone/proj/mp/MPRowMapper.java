package com.capstone.proj.mp;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MPRowMapper implements RowMapper<MP> {

    @Override
    public MP mapRow(ResultSet rs, int rowNum) throws SQLException {
        MP mp = new MP(
                rs.getInt("gov_id"),
                rs.getString("name"),
                rs.getString("image_url"),
                rs.getString("party"),
                rs.getString("constituency_name"),
                rs.getInt("constituency_id"),
                rs.getString("email_address"),
                rs.getString("twitter")
        );
        return mp;
    }

}
