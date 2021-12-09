package com.capstone.proj.county;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/counties")
public class CountyController {

    private CountyService countyService;

    @Autowired
    public CountyController(CountyService countyService){
        this.countyService = countyService;
    }

    



}
