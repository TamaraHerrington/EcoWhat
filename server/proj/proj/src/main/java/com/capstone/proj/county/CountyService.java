package com.capstone.proj.county;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@Service
public class CountyService {

    private CountyDAO countyDAO;

    @Autowired
    public CountyService(CountyDAO countyDAO){
        this.countyDAO = countyDAO;
    }

    public JsonNode getFromApiTest(String postcode) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://api.postcodes.io/postcodes/" + postcode;
        ResponseEntity<JsonNode> response
                = restTemplate.getForEntity(fooResourceUrl, JsonNode.class);
        JsonNode responseObj = response.getBody();

        return responseObj.get("result").get("parliamentary_constituency");
    }

}
