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
    private Integer govId;
    private String name;
    private String photoLink;
    private String party;
    private String constituencyName;
    private Integer constituencyId;
    private String emailAddress;
    private String twitter;


    public MP(Integer govId, String name, String photoLink, String party, String constituencyName,
              Integer constituencyId, String emailAddress, String twitter) {
        this.govId = govId;
        this.name = name;
        this.photoLink = photoLink;
        this.party = party;
        this.constituencyName = constituencyName;
        this.constituencyId = constituencyId;
        this.emailAddress = emailAddress;
        this.twitter = twitter;
    }
}
