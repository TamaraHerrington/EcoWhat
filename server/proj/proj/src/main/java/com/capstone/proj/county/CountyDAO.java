package com.capstone.proj.county;

import java.util.ArrayList;

public interface CountyDAO {

    public void addAllCountyNames(ArrayList<String> listOfNames);

    public void addCountyConstituencies(String county, int[] arrayOfConstituencies);

    public void addCountyTable();

    public void dropCountyTable();

    public void addDataNotInAPI(String countyName, int[] constituencyIds);
}
