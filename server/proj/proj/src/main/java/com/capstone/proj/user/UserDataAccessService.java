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
                INSERT INTO users (first_name, last_name, email, password, constituency_id)
                VALUES (?, ?, ?, crypt(?, gen_salt('bf', 8)), ?);
                """;
        return jdbcTemplate.update(
                sql,
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getConstituencyId()
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

    // || ===================  Login Authentication ===================== ||

    public Optional<User> authenticateLogin(String email, String password) {
        String sql = """
                SELECT * FROM users
                WHERE email = ? AND password = crypt(?, password);
                """;
        return jdbcTemplate.query(sql, userRowMapper, email, password)
                .stream()
                .findFirst();
    }

    @Override
    public int updateUserToken(User user) {
        String sql = """
                UPDATE users
                SET token = ?
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, user.getToken(), user.getId());
    }

    @Override
    public Optional<User> findByToken(String token) {
        String sql = """
                SELECT * FROM users
                WHERE token = ?;
                """;
        return jdbcTemplate.query(sql, userRowMapper, token)
                .stream()
                .findFirst();
    }

    @Override
    public int removeTokenOnLogOut(String token) {
        String sql = """
                UPDATE users
                SET token = null
                WHERE token = ?;
                """;
        return jdbcTemplate.update(sql, token);
    }

}
