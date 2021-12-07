package com.capstone.proj.vote;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VoteDataAccessService implements VoteDAO{

    private JdbcTemplate jdbcTemplate;

    public VoteDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

}
