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

    @GetMapping("/{postcode}")
    public String getConstituencyFromPostcode(@PathVariable("postcode") String postcode){
        try{
            return countyService.getConstituencyFromPostcode(postcode);
        } catch (JSONException e) {
            return "ERROR" + e.getMessage();
        }

    }

    @PostMapping("/addallcountynames")
    public void addAllCountyNames(){
        countyService.addAllCountyNames();
    }

    @PostMapping("/addcountyconstituencies")
    public void addCountyConstituencies(){
        countyService.addCountyConstituencies();
    }



}
