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
    }

    @Override
    public void addCountyTable(){
        String sql = """
                CREATE TABLE counties (id BIGSERIAL PRIMARY KEY, county_name VARCHAR(255), constituency_ids INTEGER[]);
                """;
        jdbcTemplate.execute(sql);
    }

    @Override
    public void dropCountyTable(){
        String sql = """
                DROP TABLE IF EXISTS counties;
                """;
        jdbcTemplate.execute(sql);
    }

    public void addDataNotInAPI(String countyName, int[] constituencyIds){
        String sql = """
                UPDATE counties SET constituency_ids = ? WHERE county_name = ?
                """;
        jdbcTemplate.update(sql, constituencyIds, countyName);
    }

}
