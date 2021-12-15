package com.capstone.proj.userCommentVotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCommentVoteDataAccessService implements UserCommentVoteDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserCommentVoteDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addVote(int user_id, int comment_id, int upvote, int downvote){

        String sql = """
                INSERT INTO userCommentVotes 
                (user_id, comment_id, upvoted, downvoted)
                VALUES
                (?, ?, ?, ?);
                """;

        jdbcTemplate.update(sql, user_id, comment_id, upvote, downvote);
    }

    @Override
    public List<UserCommentVote> checkVotes(int user_id, int comment_id){

        String sql = """
                SELECT * FROM userCommentVotes WHERE user_id = ? AND comment_id = ?;
                """;
        List<UserCommentVote> userCommentVote = jdbcTemplate.query(sql, new UserCommentVoteRowMapper(), user_id, comment_id);
        return userCommentVote;

    }

    @Override
    public void unvote(int user_id, int comment_id, int upvote, int downvote){

        String sql = """
                UPDATE userCommentVotes SET upvoted = upvoted - ?, downvoted = downvoted - ? WHERE user_id = ? AND comment_id = ?;
                UPDATE comments SET upvotes = upvotes - ?, downvotes = downvotes - ? WHERE id = ?;
                """;
        jdbcTemplate.update(sql, upvote, downvote, user_id, comment_id, upvote, downvote, comment_id);

    }

    @Override
    public void editVote(int user_id, int comment_id, int upvote, int downvote){

        String sql = """
                UPDATE userCommentVotes SET upvoted = upvoted + ?, downvoted = downvoted + ? WHERE user_id = ? AND comment_id = ?;
                UPDATE comments SET upvotes = upvotes + ?, downvotes = downvotes + ? WHERE id = ?;
                """;
        jdbcTemplate.update(sql, upvote, downvote, user_id, comment_id, upvote, downvote, comment_id);
    }

}
