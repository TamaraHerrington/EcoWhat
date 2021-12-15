package com.capstone.proj.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentService {

    private EnvironmentDAO environmentDAO;

    @Autowired
    public EnvironmentService(EnvironmentDAO environmentDAO){
        this.environmentDAO = environmentDAO;
    }

    public Environment getEnvironmentDataByName(String name) {
        return environmentDAO.getEnvironmentByName(name);
    }
}
