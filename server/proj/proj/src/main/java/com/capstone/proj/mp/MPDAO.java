package com.capstone.proj.mp;

import java.util.Optional;

public interface MPDAO {

    public void addMps(MP mp);

    public void createMpTable();

    public void dropMpTable();

    public Optional<MP> getMpByConstituencyId(int id);
}
