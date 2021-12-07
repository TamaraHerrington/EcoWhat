package com.capstone.proj.comment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Comment {
    private Integer id;
    private Integer userId;
    private String comment;
    private Integer upvotes;
    private Integer downvotes;
    private Integer constituencyId;

    public Comment(int id, Integer userId, String comment, Integer upvotes, Integer downvotes, Integer constituencyId) {
        this.userId = userId;
        this.comment = comment;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.constituencyId = constituencyId;
    }

}
