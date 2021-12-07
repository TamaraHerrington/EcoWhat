package com.capstone.proj.county;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountyRowMapper implements RowMapper<County> {

    @Override
    public County mapRow(ResultSet rs, int rowNum) throws SQLException {
        County county = new County(
                rs.getInt("id"),
                rs.getString("name"),
//                empty list for now, will need to add constituency ids
                new ArrayList<Integer>()
        );
        return county;
    }
}
