package com.capstone.proj.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgresUser")
public class UserDataAccessService implements UserDAO{

    private JdbcTemplate jdbcTemplate;
    private UserRowMapper userRowMapper;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = new UserRowMapper();
    }

    @Override
    public int createUser(User user) {
        String sql = """
                INSERT INTO users (first_name, last_name, email, password)
                VALUES (?, ?, ?, ?);
                """;
        return jdbcTemplate.update(
                sql,
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword()
        );
    }

    @Override
    public List<User> getAllUsers() {
        String sql = """
                SELECT * FROM users;
                """;
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public Optional<User> getUserById(int id) {
        String sql = """
                SELECT * FROM users
                WHERE id = ?;
                """;
        return jdbcTemplate.query(sql, userRowMapper, id)
                .stream()
                .findFirst();
    }

    @Override
    public int updateUser(int id, User user) {
        String sql = """
                UPDATE users
                SET first_name = ?, last_name = ?, email = ?, password = ?
                WHERE id = ?;
                """;
        return jdbcTemplate.update(
                sql,
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
                id
        );
    }

    @Override
    public int deleteUser(int id) {
        String sql = """
                DELETE FROM users
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }

}
