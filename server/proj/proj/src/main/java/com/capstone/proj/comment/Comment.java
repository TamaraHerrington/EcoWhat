package com.capstone.proj.comment;

import java.util.Objects;

public class Comment {
    private Integer userId;
    private String comment;
    private Integer upvotes;
    private Integer downvotes;
    private Integer constituencyId;

    public Comment(Integer userId, String comment, Integer upvotes, Integer downvotes, Integer constituencyId) {
        this.userId = userId;
        this.comment = comment;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.constituencyId = constituencyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public Integer getDownvotes() {
        return downvotes;
    }

    public Integer getConstituencyId() {
        return constituencyId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    public void setConstituencyId(Integer constituencyId) {
        this.constituencyId = constituencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return Objects.equals(userId, comment1.userId) && Objects.equals(comment, comment1.comment) && Objects.equals(upvotes, comment1.upvotes) && Objects.equals(downvotes, comment1.downvotes) && Objects.equals(constituencyId, comment1.constituencyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, comment, upvotes, downvotes, constituencyId);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "userId=" + userId +
                ", comment='" + comment + '\'' +
                ", upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                ", constituencyId=" + constituencyId +
                '}';
    }
}
