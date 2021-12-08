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
    public void fromApiTest(@PathVariable("postcode") String postcode){
        try{
            System.out.println(countyService.getFromApiTest(postcode));
        } catch (JSONException e) {
            System.out.println("ERROR" + e.getMessage());
        }

    }

    @PostMapping("/addallcountynames")
    public void addAllCountyNames(){
        countyService.addAllCountyNames();
    }

    @PostMapping("/addcountycostituencies")
    public void addCountyConstituencies(){
        countyService.addCountyConstituencies();
    }



}
