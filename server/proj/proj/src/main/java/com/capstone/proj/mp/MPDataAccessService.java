package com.capstone.proj.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MPDataAccessService implements MPDAO{

    private JdbcTemplate jdbcTemplate;
    private MPRowMapper mpRowMapper;

    @Autowired
    public MPDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.mpRowMapper = new MPRowMapper();
    }

    public void addMps(MP mp){
        String sql = """
                INSERT INTO mps(name, constituency_id,
                constituency_name, party, gov_id,
                email_address, twitter, image_url)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                """;
        jdbcTemplate.update(sql, mp.getName(), mp.getConstituencyId(), mp.getConstituencyName(),
                mp.getParty(), mp.getGovId(), mp.getEmailAddress(), mp.getTwitter(), mp.getPhotoLink());

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

    @Override
    public Optional<MP> getMpByConstituencyId(int id){
        String sql = """
                SELECT * FROM mps WHERE constituency_id = ?
                """;
        return jdbcTemplate.query(sql, mpRowMapper, id).stream().findFirst();
    }
}
