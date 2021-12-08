package com.capstone.proj.vote;

import java.util.ArrayList;

public interface VoteDAO {

    public ArrayList<Vote> getByMP(String mp);
}
