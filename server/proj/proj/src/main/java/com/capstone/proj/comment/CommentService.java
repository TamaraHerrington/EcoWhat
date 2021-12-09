package com.capstone.proj.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
}
