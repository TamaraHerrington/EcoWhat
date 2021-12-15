package com.capstone.proj.comment;

import com.capstone.proj.user.User;
import com.capstone.proj.user.UserDAO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {

    private UserDAO userDAO;

    public CommentRowMapper(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public CommentRowMapper() {
    }

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment(
                rs.getInt("id"),
                rs.getInt("user_id"),
                userDAO.getUserById(rs.getInt("user_id")).get().getFirstName(),
                rs.getString("comment_title"),
                rs.getString("comment"),
                rs.getString("comment_category"),
                rs.getInt("upvotes"),
                rs.getInt("downvotes"),
                rs.getInt("constituency_id"),
                rs.getString("post_date")

        );
        return comment;
    }
}
