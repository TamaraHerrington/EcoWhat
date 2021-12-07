package com.capstone.proj.county;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CountyService {

    private CountyDAO countyDAO;

    @Autowired
    public CountyService(CountyDAO countyDAO){
        this.countyDAO = countyDAO;
    }

}
