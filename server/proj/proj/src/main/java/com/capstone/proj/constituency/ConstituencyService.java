package com.capstone.proj.constituency;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class ConstituencyService {

    private ConstituencyDAO constituencyDAO;

    @Autowired
    public ConstituencyService(ConstituencyDAO constituencyDAO){
        this.constituencyDAO = constituencyDAO;
    }

    public void addAllConstituencies(){
//        get data from api
//
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://members-api.parliament.uk/api/Location/Browse/1/England";
        ResponseEntity<JsonNode> response
                = restTemplate.getForEntity(fooResourceUrl, JsonNode.class);
        JsonNode responseObj = response.getBody();
        ArrayList<Constituency> ListOfConstituencies = new ArrayList<Constituency>();
        JsonNode constituencies = responseObj.get("value").get("childContexts");
        for (JsonNode constituency : constituencies){
            constituencyDAO.addConstituency(constituency.get("id").intValue(),
                    constituency.get("name").textValue());

//            ListOfConstituencies.add(new Constituency(constituency.get("id").intValue(),
//                    constituency.get("name").textValue()));
        }
        System.out.println(ListOfConstituencies);

        String fooResourceUrlWales
                = "https://members-api.parliament.uk/api/Location/Browse/1/Wales";
        ResponseEntity<JsonNode> responseWales
                = restTemplate.getForEntity(fooResourceUrlWales, JsonNode.class);
        JsonNode responseObjWales = responseWales.getBody();
        ArrayList<Constituency> ListOfConstituenciesWales = new ArrayList<Constituency>();
        JsonNode constituenciesWales = responseObjWales.get("value").get("childContexts");
        for (JsonNode constituency : constituenciesWales){
            constituencyDAO.addConstituency(constituency.get("id").intValue(),
                    constituency.get("name").textValue());

//            ListOfConstituencies.add(new Constituency(constituency.get("id").intValue(),
//                    constituency.get("name").textValue()));
        }

//        add to dao


    }

}
