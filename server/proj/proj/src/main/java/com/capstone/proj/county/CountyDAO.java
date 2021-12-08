package com.capstone.proj.county;

import java.util.ArrayList;

public interface CountyDAO {

    public void addAllCountyNames(ArrayList<String> listOfNames);

    public void addCountyConstituencies(ArrayList<Integer> listOfConstituencies);
}
