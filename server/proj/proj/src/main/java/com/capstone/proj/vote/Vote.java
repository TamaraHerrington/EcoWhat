package com.capstone.proj.vote;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Vote {
    private Integer id;
    private String issue;
    private String details;
    private Integer mpId;
    private Boolean votedFor;

    public Vote(Integer id, String issue, String details, Integer mpId, Boolean votedFor) {
        this.issue = issue;
        this.details = details;
        this.mpId = mpId;
        this.votedFor = votedFor;
    }

}
