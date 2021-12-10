package com.capstone.proj.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public void addComment(@RequestBody Comment comment){
        commentService.addComment(comment);

    }

    @PutMapping("/upvote/{id}")
    public void upvoteComment(@PathVariable("id") int id){
        commentService.upvoteComment(id);
    }

    @PutMapping("/downvote/{id}")
    public void downvoteComment(@PathVariable("id") int id){
        commentService.downvoteComment(id);
    }


}
