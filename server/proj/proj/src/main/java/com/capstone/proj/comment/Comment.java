package com.capstone.proj.comment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Comment {
    private Integer id;
    private Integer userId;
    private String comment_title;
    private String comment;
    private String comment_category;
    private Integer upvotes;
    private Integer downvotes;
    private Integer constituencyId;
    private String post_date;

    public Comment(Integer id, Integer userId, String comment_title,
                   String comment, String comment_category, Integer upvotes, Integer downvotes, Integer constituencyId,
                   String post_date) {
        this.id = id;
        this.userId = userId;
        this.comment_title = comment_title;
        this.comment = comment;
        this.comment_category = comment_category;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.constituencyId = constituencyId;
        this.post_date = post_date;
    }
}
