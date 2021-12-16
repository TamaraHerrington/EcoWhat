package com.capstone.proj.userCommentVotes;

import com.capstone.proj.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCommentVoteService {

    private UserCommentVoteDAO userCommentVoteDAO;
    private CommentService commentService;

    @Autowired
    public UserCommentVoteService(UserCommentVoteDAO userCommentVoteDAO, CommentService commentService) {
        this.userCommentVoteDAO = userCommentVoteDAO;
        this.commentService = commentService;
    }




    public void addVote(int user_id, int comment_id, int upvote, int downvote) {
        if((upvote == 1 && downvote == 1) || (upvote == 0 && downvote == 0)){
            return;
        }
        if(upvote ==1){
            List <UserCommentVote> previousVote = userCommentVoteDAO.checkVotes(user_id, comment_id);
            if(previousVote.size() > 0) {
                if (previousVote.get(0).upvoted == 1) {
                    unvote(user_id, comment_id, upvote, downvote);

                } else if (previousVote.get(0).upvoted == 0) {
                    if(previousVote.get(0).downvoted == 1) {
                        downvote = -1;
                    }
                    editVote(user_id, comment_id, upvote, downvote);
                }
            }else {
                userCommentVoteDAO.addVote(user_id, comment_id, upvote, downvote);
                commentService.upvoteComment(comment_id);
            }
        }
        if(downvote == 1) {
            List<UserCommentVote> previousVote = userCommentVoteDAO.checkVotes(user_id, comment_id);
            if(previousVote.size() > 0) {
                if (previousVote.get(0).downvoted == 1) {
                    unvote(user_id, comment_id, upvote, downvote);
                } else if (previousVote.get(0).downvoted == 0) {
                    if(previousVote.get(0).upvoted == 1) {
                        upvote = -1;
                    }
                    editVote(user_id, comment_id, upvote, downvote);
                }
            }else{
                userCommentVoteDAO.addVote(user_id, comment_id, upvote, downvote);
                commentService.downvoteComment(comment_id);
            }
        }
    }

    public List<UserCommentVote> checkVotes(int user_id, int comment_id) {
        return userCommentVoteDAO.checkVotes(user_id, comment_id);
    }

    public void unvote(int user_id, int comment_id, int upvote, int downvote){
        userCommentVoteDAO.unvote(user_id, comment_id, upvote, downvote);
    }

    public void editVote(int user_id, int comment_id, int upvote, int downvote){
        userCommentVoteDAO.editVote(user_id, comment_id, upvote, downvote);
    }
}
