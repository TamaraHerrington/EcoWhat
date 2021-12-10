package com.capstone.proj.constituency;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ConstituencyDataAccessService implements ConstituencyDAO{

    private JdbcTemplate jdbcTemplate;

    public ConstituencyDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addConstituency(int id, String name){
        String sql = """
                INSERT INTO constituencies (constituency_id, constituency_name)
                VALUES (?, ?)
                """;

        jdbcTemplate.update(sql, id, name);
    }

    @Override
    public void createConstituencyTable(){
        String sql = """
                CREATE TABLE constituencies (id BIGSERIAL, constituency_name VARCHAR(255), constituency_id INTEGER);
                """;
        jdbcTemplate.execute(sql);
    }

    @Override
    public void dropConstituencyTable(){
        String sql = """
                DROP TABLE IF EXISTS constituencies;
                """;
        jdbcTemplate.execute(sql);
    }

    @Override
    public String getCountyFromConstituency(int constituency_id){
        String sql = """
                SELECT county_name FROM counties WHERE ? = ANY (constituency_ids) ;
                """;
        return jdbcTemplate.queryForObject(sql, new Object[] {constituency_id}, String.class);
    }

    @Override
    public List<Constituency> getAllConstituencies(){
        String sql =  """
                SELECT * FROM constituencies;
                """;
        List<Constituency> allConstituencies = jdbcTemplate.query(sql, new ConstituencyRowMapper());
        return allConstituencies;
    };

    public String getConstituencyNameFromId(int constituency_id){
        String sql = """
                SELECT constituency_name FROM constituencies WHERE constituency_id =  ?;
                """;
        return jdbcTemplate.queryForObject(sql, new Object[] {constituency_id}, String.class);
    };

    public Integer getConstituencyIdFromName(String name){
        String sql = """
                SELECT constituency_id FROM constituencies WHERE constituency_name LIKE ?;
                """;

        return jdbcTemplate.queryForObject(sql, new Object[]{name}, Integer.class);
    }

}
