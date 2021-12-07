package com.capstone.proj.constituency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ConstituencyService {

    private ConstituencyDAO constituencyDAO;

    @Autowired
    public ConstituencyService(ConstituencyDAO constituencyDAO){
        this.constituencyDAO = constituencyDAO;
    }

}
