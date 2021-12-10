package com.capstone.proj.comment;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDataAccessService implements CommentDAO{

    private JdbcTemplate jdbcTemplate;

    public CommentDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addComment(Comment comment){
        String sql = """
                INSERT INTO comments 
                (user_id, comment_title, comment, comment_category, upvotes, downvotes, constituency_id, post_date)
                VALUES
                (?, ?, ?, ?, ?, ?, ?, ?);
                """;

        jdbcTemplate.update(sql, comment.getUserId(), comment.getComment_title(), comment.getComment_category(),
                comment.getUpvotes(), comment.getDownvotes(), comment.getConstituencyId(), comment.getPost_date());
    }

}
