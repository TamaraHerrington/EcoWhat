package com.capstone.proj.comment;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDataAccessService implements CommentDAO{

    private JdbcTemplate jdbcTemplate;

    public CommentDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

}
