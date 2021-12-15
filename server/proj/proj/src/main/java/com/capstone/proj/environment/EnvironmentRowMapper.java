package com.capstone.proj.environment;

import com.capstone.proj.constituency.Constituency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnvironmentRowMapper implements RowMapper<Environment> {

    @Override
    public Environment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Environment environment = new Environment(
                rs.getInt("id"),
        rs.getString("county_name"),
        rs.getString("nitrogen_dioxide"),
        rs.getString("particulate_matter"),
        rs.getString("herbicides"),
        rs.getString("fungicides"),
        rs.getString("dbps_winter"),
        rs.getString("dbps_summer")
        );
        return environment;
    }
}
