package com.capstone.proj.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/environment")
public class EnvironmentController {

    private EnvironmentService environmentService;

    @Autowired
    public EnvironmentController(EnvironmentService environmentService){
        this.environmentService = environmentService;
    }

    @GetMapping("/{name}")
    public Environment getEnvironmentDataByName(@PathVariable("name") String name){
        return environmentService.getEnvironmentDataByName(name);
    }
}
