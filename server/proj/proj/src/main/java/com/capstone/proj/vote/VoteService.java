package com.capstone.proj.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private VoteDAO voteDAO;

    @Autowired
    public VoteService(VoteDAO voteDAO){
        this.voteDAO = voteDAO;
    }

}
