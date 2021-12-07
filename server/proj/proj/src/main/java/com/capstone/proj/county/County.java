package com.capstone.proj.county;

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
public class County {
    private Integer id;
    private String name;
    private List<Integer> constituencyIds;

    public County(int id, String name, List<Integer> constituencyIds) {
        this.name = name;
        this.constituencyIds = constituencyIds;
    }


}
