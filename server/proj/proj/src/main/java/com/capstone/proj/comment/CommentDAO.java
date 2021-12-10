package com.capstone.proj.comment;

public interface CommentDAO {

    public void addComment(Comment comment);

    public void upvoteComment(int id);

    public void downvoteComment(int id);
}
