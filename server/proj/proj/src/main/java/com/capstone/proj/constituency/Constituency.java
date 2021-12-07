package com.capstone.proj.constituency;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Constituency {
    private Integer id;
    private String name;

    public Constituency(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
