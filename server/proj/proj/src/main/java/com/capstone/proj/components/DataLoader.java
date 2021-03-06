package com.capstone.proj.components;

import com.capstone.proj.constituency.ConstituencyService;
import com.capstone.proj.county.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    CountyService countyService;

    @Autowired
    ConstituencyService constituencyService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        countyService.dropCountyTable();
        constituencyService.dropConstituencyTable();
        countyService.createCountyTable();
        constituencyService.createConstituencyTable();
        countyService.addAllCountyNames();
        countyService.addCountyConstituencies();
        countyService.addDataNotInAPI();
        constituencyService.addAllConstituencies();
        System.out.println("Application runner complete");
    }
}
