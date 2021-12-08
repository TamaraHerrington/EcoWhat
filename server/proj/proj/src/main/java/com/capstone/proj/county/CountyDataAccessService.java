package com.capstone.proj.county;

import org.json.JSONArray;
import org.springframework.data.relational.core.sql.SQL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Array;

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

    @Override
    public void addCountyConstituencies(String county, int[] arrayOfConstituencies){
        String sql = """
                UPDATE counties SET constituency_ids = ? WHERE county_name = ?
                """;
        jdbcTemplate.update(sql, arrayOfConstituencies, county);
    };

}
