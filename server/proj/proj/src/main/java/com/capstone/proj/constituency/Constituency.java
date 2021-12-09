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
    private Integer constituency_id;
    private String constituency_name;

    public Constituency(Integer constituency_id, String constituency_name) {
        this.constituency_id = constituency_id;
        this.constituency_name = constituency_name;
    }

}
