package com.capstone.proj.vote;

import java.util.Objects;

public class Vote {
    private String issue;
    private String details;
    private Integer mpId;
    private Boolean votedFor;

    public Vote(String issue, String details, Integer mpId, Boolean votedFor) {
        this.issue = issue;
        this.details = details;
        this.mpId = mpId;
        this.votedFor = votedFor;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getMpId() {
        return mpId;
    }

    public void setMpId(Integer mpId) {
        this.mpId = mpId;
    }

    public Boolean getVotedFor() {
        return votedFor;
    }

    public void setVotedFor(Boolean votedFor) {
        this.votedFor = votedFor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(issue, vote.issue) && Objects.equals(details, vote.details) && Objects.equals(mpId, vote.mpId) && Objects.equals(votedFor, vote.votedFor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issue, details, mpId, votedFor);
    }

    @Override
    public String toString() {
        return "Vote{" +
                "issue='" + issue + '\'' +
                ", details='" + details + '\'' +
                ", mpId=" + mpId +
                ", votedFor=" + votedFor +
                '}';
    }
}
