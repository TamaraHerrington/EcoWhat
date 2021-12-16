package com.capstone.proj.userCommentVotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usercommentvotes")
public class UserCommentVoteController {

    private UserCommentVoteService userCommentVoteService;

    @Autowired
    public UserCommentVoteController(UserCommentVoteService userCommentVoteService) {
        this.userCommentVoteService = userCommentVoteService;
    }

    @PutMapping
    public void addVote(@RequestBody  UserCommentVote userCommentVote){
        userCommentVoteService.addVote(userCommentVote.getUser_id(), userCommentVote.getComment_id(), userCommentVote.getUpvoted(), userCommentVote.getDownvoted());
    }

    @GetMapping("/{user_id}/{comment_id}")
    public List <UserCommentVote> checkVotes(@PathVariable("user_id") int user_id, @PathVariable("comment_id") int comment_id ){
        return userCommentVoteService.checkVotes(user_id, comment_id);
    }

}
