package com.capstone.proj.user;

import java.util.Objects;

public class User {
    private String username;
    private String email;
    private String password;
    private Integer constituencyId;

    public User(String username, String email, String password, Integer constituencyId) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.constituencyId = constituencyId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(Integer constituencyId) {
        this.constituencyId = constituencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(constituencyId, user.constituencyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, constituencyId);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", constituencyId=" + constituencyId +
                '}';
    }
}
