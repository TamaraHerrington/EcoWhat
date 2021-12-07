package com.capstone.proj.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MPService {

    private MPDAO mpDAO;

    @Autowired
    public MPService(MPDAO mpDAO){
        this.mpDAO = mpDAO;
    }

}
