package com.capstone.proj.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private CommentDAO commentDAO;

    @Autowired
    public CommentService(CommentDAO commentDAO){
        this.commentDAO = commentDAO;
    }

    public void addComment(Comment comment) {
        commentDAO.addComment(comment);
    }

    public void upvoteComment(int id) {
        commentDAO.upvoteComment(id);
    }

    public void downvoteComment(int id) {
        commentDAO.downvoteComment(id);
    }

    public List<Comment> getAllComments() {
        return commentDAO.getAllComments();
    }

    public List<Comment> getCommentsByUser(int id) {
        return commentDAO.getCommentsByUser(id);
    }

    public List<Comment> getCommentsByConstituency(int id) {
        return commentDAO.getCommentsByConstituency(id);
    }
}
