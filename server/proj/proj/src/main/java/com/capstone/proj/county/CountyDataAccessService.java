package com.capstone.proj.county;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CountyDataAccessService implements CountyDAO{

    private JdbcTemplate jdbcTemplate;

    public CountyDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

}
