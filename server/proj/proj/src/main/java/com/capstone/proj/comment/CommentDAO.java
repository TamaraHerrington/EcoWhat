package com.capstone.proj.comment;

import java.util.List;


public interface CommentDAO {

    public void addComment(Comment comment);

    public void upvoteComment(int id);

    public void downvoteComment(int id);

    public List<Comment> getAllComments();

    public List<Comment> getCommentsByUser(int id);

    public List<Comment> getCommentsByConstituency(int id);

    public void deleteCommentById(int id);

    public void editCommentById(Comment newComment, int id);
}
