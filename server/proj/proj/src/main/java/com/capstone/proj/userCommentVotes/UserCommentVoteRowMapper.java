package com.capstone.proj.userCommentVotes;

import com.capstone.proj.userCommentVotes.UserCommentVote;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCommentVoteRowMapper implements RowMapper<UserCommentVote> {

    public UserCommentVoteRowMapper() {
    }

    @Override
    public UserCommentVote mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserCommentVote userCommentVote = new UserCommentVote(
                rs.getInt("id"),
                rs.getInt("comment_id"),
                rs.getInt("user_id"),
                rs.getInt("upvoted"),
                rs.getInt("downvoted")
        );
        return userCommentVote;
    }
}