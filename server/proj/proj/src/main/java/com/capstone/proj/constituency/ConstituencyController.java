package com.capstone.proj.constituency;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/constituencies")
public class ConstituencyController {

    private ConstituencyService constituencyService;

    @Autowired
    public ConstituencyController(ConstituencyService constituencyService){
        this.constituencyService = constituencyService;
    }

    @GetMapping("/{constituency_id}/county")
    public String getCountyFromConstituency(@PathVariable("constituency_id") int constituency_id){
        return constituencyService.getCountyFromConstituency(constituency_id);


    }

}
