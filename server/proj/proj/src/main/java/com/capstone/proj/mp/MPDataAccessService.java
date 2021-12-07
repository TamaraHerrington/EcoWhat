package com.capstone.proj.mp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MPDataAccessService implements MPDAO{

    private JdbcTemplate jdbcTemplate;

    public MPDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
