package com.capstone.proj.county;

import java.util.List;
import java.util.Objects;

public class County {
    private String name;
    private List<Integer> constituencyIds;

    public County(String name, List<Integer> constituencyIds) {
        this.name = name;
        this.constituencyIds = constituencyIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getConstituencyIds() {
        return constituencyIds;
    }

    public void setConstituencyIds(List<Integer> constituencyIds) {
        this.constituencyIds = constituencyIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        County county = (County) o;
        return Objects.equals(name, county.name) && Objects.equals(constituencyIds, county.constituencyIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, constituencyIds);
    }

    @Override
    public String toString() {
        return "County{" +
                "name='" + name + '\'' +
                ", constituencyIds=" + constituencyIds +
                '}';
    }
}
