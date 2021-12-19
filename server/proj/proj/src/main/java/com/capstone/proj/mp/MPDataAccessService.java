package com.capstone.proj.mp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MPDataAccessService implements MPDAO{

    private JdbcTemplate jdbcTemplate;

    public MPDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addMps(MP mp){

    }

    public void createMpTable(){
//        ADD TO POJO: constituency name, party, government id
        String sql = """
                CREATE TABLE mps (id BIGSERIAL PRIMARY KEY, name VARCHAR(255), constituency_id INTEGER,
                constituency_name VARCHAR(255), party VARCHAR(255), gov_id INTEGER,
                email_address VARCHAR(255), twitter VARCHAR(255), image_url VARCHAR(255) );
                """;
        jdbcTemplate.execute(sql);
    }

    @Override
    public void dropMpTable() {
        String sql = """
                DROP TABLE IF EXISTS mps;
                """;
        jdbcTemplate.execute(sql);
    }
}
