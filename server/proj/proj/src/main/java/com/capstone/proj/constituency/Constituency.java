package com.capstone.proj.constituency;

import java.util.List;
import java.util.Objects;

public class Constituency {
    private String name;
    private List<String> counties;

    public Constituency(String name, List<String> counties) {
        this.name = name;
        this.counties = counties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCounties() {
        return counties;
    }

    public void setCounties(List<String> counties) {
        this.counties = counties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constituency that = (Constituency) o;
        return Objects.equals(name, that.name) && Objects.equals(counties, that.counties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, counties);
    }

    @Override
    public String toString() {
        return "Constituency{" +
                "name='" + name + '\'' +
                ", counties=" + counties +
                '}';
    }
}
