package com.capstone.proj.constituency;

import org.apache.tomcat.util.bcel.Const;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/all")
    public List<Constituency> getAllConstituencies(){
        return constituencyService.getAllConstituencies();
    }

    @GetMapping("/{constituency_id}")
    public String getConstituencyNameFromId(@PathVariable("constituency_id") int constituency_id){
        return constituencyService.getConstituencyNameFromId(constituency_id);
    }
}
