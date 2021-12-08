package com.capstone.proj.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    private VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService){
        this.voteService = voteService;
    }

    @GetMapping("/{mp}")
    public ArrayList<Vote> getByMP(@PathVariable("mp") String mp){
        return voteService.getByMP(mp);

    }

}
