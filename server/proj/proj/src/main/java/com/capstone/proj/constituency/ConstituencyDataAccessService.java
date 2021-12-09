package com.capstone.proj.constituency;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
                DROP TABLE constituencies
                """;
        jdbcTemplate.execute(sql);
    }

}
