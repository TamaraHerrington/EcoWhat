package com.capstone.proj.constituency;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConstituencyDataAccessService implements ConstituencyDAO{

    private JdbcTemplate jdbcTemplate;

    public ConstituencyDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

}
