package com.capstone.proj.mp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MP {
    private Integer id;
    private String name;
    private Integer constituencyId;
    private String emailAddress;
    private String twitter;
    private String photoLink;

    public MP(String name, Integer constituencyId, String emailAddress, String twitter, String photoLink) {
        this.name = name;
        this.constituencyId = constituencyId;
        this.emailAddress = emailAddress;
        this.twitter = twitter;
        this.photoLink = photoLink;
    }

}
