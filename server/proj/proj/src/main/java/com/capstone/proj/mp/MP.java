package com.capstone.proj.mp;

import java.util.Objects;

public class MP {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(Integer constituencyId) {
        this.constituencyId = constituencyId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MP mp = (MP) o;
        return Objects.equals(name, mp.name) && Objects.equals(constituencyId, mp.constituencyId) && Objects.equals(emailAddress, mp.emailAddress) && Objects.equals(twitter, mp.twitter) && Objects.equals(photoLink, mp.photoLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, constituencyId, emailAddress, twitter, photoLink);
    }

    @Override
    public String toString() {
        return "MP{" +
                "name='" + name + '\'' +
                ", constituencyId=" + constituencyId +
                ", emailAddress='" + emailAddress + '\'' +
                ", twitter='" + twitter + '\'' +
                ", photoLink='" + photoLink + '\'' +
                '}';
    }
}
