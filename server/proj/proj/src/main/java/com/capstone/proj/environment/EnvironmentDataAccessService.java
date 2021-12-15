package com.capstone.proj.environment;

import com.capstone.proj.user.UserDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EnvironmentDataAccessService implements EnvironmentDAO{

    private JdbcTemplate jdbcTemplate;


    public EnvironmentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Environment getEnvironmentByName(String name){
        String sql = """
                SELECT * FROM environment WHERE county_name LIKE ?;
                """;

        return jdbcTemplate.queryForObject(sql, new EnvironmentRowMapper(), name);

    }
}
