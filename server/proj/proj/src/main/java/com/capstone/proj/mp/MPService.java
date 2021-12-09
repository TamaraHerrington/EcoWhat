package com.capstone.proj.mp;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MPService {

    private MPDAO mpDAO;

    @Autowired
    public MPService(MPDAO mpDAO){
        this.mpDAO = mpDAO;
    }

    public void addMps(){
//        get constituency ids from the database

//        hard coding an example id for now
        int constituencyId = 3869;

//        use api to get info on that constituency's mp
        RestTemplate restTemplate = new RestTemplate();
        String mpResourceUrl
                = "https://members-api.parliament.uk/api/Members/Search?ConstituencyId=" + constituencyId;
        ResponseEntity<JsonNode> response
                = restTemplate.getForEntity(mpResourceUrl, JsonNode.class);
        JsonNode responseObj = response.getBody();
        System.out.println(responseObj.get("items"));
//        non-active members have a .value.latestHouseMembership.membershipStatus of null, remove these, check if this
        for (JsonNode member : responseObj.get("items")){
            if (member.get("value").get("latestHouseMembership").get("membershipStatus").get("statusIsActive")!=null){
                System.out.println(member.get("value").get("nameDisplayAs"));
            }
        }
//        translates to one mp per constituency

//        filter out data we want, may need more api calls

//        add using dao
        mpDAO.addMps();
    }

}
