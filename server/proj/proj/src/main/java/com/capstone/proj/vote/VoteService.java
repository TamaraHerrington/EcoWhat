package com.capstone.proj.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VoteService {

    private VoteDAO voteDAO;

    @Autowired
    public VoteService(VoteDAO voteDAO){
        this.voteDAO = voteDAO;
    }

    public ArrayList<Vote> getByMP(String mp){
        return voteDAO.getByMP(mp);
    }


}
