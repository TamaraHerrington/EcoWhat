package com.capstone.proj.userCommentVotes;

import java.util.List;

public interface UserCommentVoteDAO {
    void addVote(int user_id, int comment_id, int upvote, int downvote);

    List<UserCommentVote> checkVotes(int user_id, int comment_id);

    void unvote(int user_id, int comment_id, int upvote, int downvote);

    void editVote(int user_id, int comment_id, int upvote, int downvote);
}
