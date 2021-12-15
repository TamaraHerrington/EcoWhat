package com.capstone.proj.user;

import com.capstone.proj.comment.Comment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties("user_name")
    private List<Comment> commentList;

}
