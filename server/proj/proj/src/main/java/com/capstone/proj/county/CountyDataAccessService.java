package com.capstone.proj.county;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CountyDataAccessService implements CountyDAO{

    private JdbcTemplate jdbcTemplate;

    public CountyDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addAllCountyNames(ArrayList<String> listOfNames){
        String sql = """
                INSERT INTO counties (county_name) VALUES (?)
                """;

        for (String county : listOfNames) {
            jdbcTemplate.update(sql, county);
        }
    }

}
