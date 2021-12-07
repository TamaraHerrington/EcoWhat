package com.capstone.proj.vote;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VoteRowMapper implements RowMapper<Vote> {

    @Override
    public Vote mapRow(ResultSet rs, int rowNum) throws SQLException {
        Vote vote = new Vote(
                rs.getString("issue"),
                rs.getString("details"),
                rs.getInt("mp_id"),
                rs.getBoolean("voted_for")
        );
        
        return vote;
    }
}
