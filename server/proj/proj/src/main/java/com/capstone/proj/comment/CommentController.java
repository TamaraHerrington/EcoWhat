package com.capstone.proj.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    };

    @GetMapping("/user/{id}")
    public List<Comment> getCommentsByUser(@PathVariable("id") int id){
        return commentService.getCommentsByUser(id);
    }

    @GetMapping("/constituency/{id}")
    public List<Comment> getCommentsByConstituency(@PathVariable("id") int id){
        return commentService.getCommentsByConstituency(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCommentById(@PathVariable("id") int id){
        commentService.deleteCommentById(id);
    }
}
