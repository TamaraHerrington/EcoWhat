package com.capstone.proj.environment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Environment {
    private int id;
    private String countyName;
    private String nitrogenDioxide;
    private String particulateMatter;
    private String herbicides;
    private String fungicides;
    private String dbpsWinter;
    private String dbpsSummer;

    public Environment(int id, String countyName, String nitrogenDioxide, String particulateMatter, String herbicides, String fungicides, String dbpsWinter, String dbpsSummer) {
        this.id = id;
        this.countyName = countyName;
        this.nitrogenDioxide = nitrogenDioxide;
        this.particulateMatter = particulateMatter;
        this.herbicides = herbicides;
        this.fungicides = fungicides;
        this.dbpsWinter = dbpsWinter;
        this.dbpsSummer = dbpsSummer;
    }
}
