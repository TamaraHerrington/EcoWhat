package com.capstone.proj.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private String token;

    public User(Long id, String firstName, String lastName, String email, String password, String token) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.token = token;
    }
}
