package com.capstone.proj.constituency;

public interface ConstituencyDAO {

    public void addConstituency(int id, String name);

    public void createConstituencyTable();

    public void dropConstituencyTable();

    public String getCountyFromConstituency(int constituency_id);
}
