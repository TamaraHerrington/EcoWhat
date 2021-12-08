package com.capstone.proj.constituency;

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

    @PostMapping
    public void addAllConstituencies(){
        constituencyService.addAllConstituencies();
    }

}
