package com.capstone.proj.user;

import com.capstone.proj.comment.Comment;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String postcode;
    private Integer constituencyId;
    private String constituencyName;
    private List<Comment> commentList;

}
