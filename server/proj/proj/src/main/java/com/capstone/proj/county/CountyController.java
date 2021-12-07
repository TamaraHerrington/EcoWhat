package com.capstone.proj.county;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/counties")
public class CountyController {

    private CountyService countyService;

    @Autowired
    public CountyController(CountyService countyService){
        this.countyService = countyService;
    }

}
