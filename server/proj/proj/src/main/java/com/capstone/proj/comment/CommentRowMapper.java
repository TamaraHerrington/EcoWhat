package com.capstone.proj.comment;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment(
                rs.getInt("id"),
                rs.getString("comment"),
                rs.getInt("upvotes"),
                rs.getInt("downvotes"),
                rs.getInt("constituency_id")
        );
        return comment;
    }
}
