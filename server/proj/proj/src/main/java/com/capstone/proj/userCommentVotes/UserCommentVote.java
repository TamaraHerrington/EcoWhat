package com.capstone.proj.userCommentVotes;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserCommentVote {
    int id;
    int comment_id;
    int user_id;
    int upvoted;
    int downvoted;

    public UserCommentVote(int id, int comment_id, int user_id, int upvoted, int downvoted) {
        this.id = id;
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.upvoted = upvoted;
        this.downvoted = downvoted;
    }
}
