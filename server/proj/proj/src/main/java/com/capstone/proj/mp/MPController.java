package com.capstone.proj.mp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/mps")
public class MPController {

    private MPService mpService;

    @Autowired
    public MPController(MPService mpService){
        this.mpService = mpService;
    }

    @PostMapping
    public void addMps(){
        mpService.addMps();
    }

    @GetMapping("/{id}")
    public Optional<MP> getMpByConstituencyId(@PathVariable("id") int id){
        return mpService.getMpByConstituencyId(id);
    }

}
