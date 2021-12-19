package com.capstone.proj.mp;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.aop.scope.ScopedProxyUtils;
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
                = "https://members-api.parliament.uk/api/Members/Search?ConstituencyId=" + constituencyId + "&IsCurrentMember=true&skip=0&take=20";
        ResponseEntity<JsonNode> response
                = restTemplate.getForEntity(mpResourceUrl, JsonNode.class);
        JsonNode responseObj = response.getBody();
        System.out.println(responseObj.get("items"));
        JsonNode mpResponse = responseObj.get("items").get(0);
        System.out.println(mpResponse);
//        non-active members have a .value.latestHouseMembership.membershipStatus of null, remove these, check if this
        System.out.println(mpResponse.get("value").get("nameDisplayAs"));
//        translates to one mp per constituency
        String name = String.valueOf(mpResponse.get("value").get("nameDisplayAs"));
        int id = mpResponse.get("value").get("id").intValue();
        System.out.println(id);
        String party = mpResponse.get("value").get("latestParty").get("name").textValue();
        System.out.println(party);
        String thumbnailUrl = mpResponse.get("value").get("thumbnailUrl").textValue();
        System.out.println(thumbnailUrl);

        RestTemplate restTemplateContact = new RestTemplate();
        String mpContactUrl
                = "https://members-api.parliament.uk/api/Members/" + id + "/Contact";
        ResponseEntity<Object[]> responseContact
                = restTemplateContact.getForEntity(mpContactUrl, Object[].class);
//        JsonNode responseContactObj = responseContact.getBody();
        System.out.println(responseContact);

//        String email = responseContactObj.get("value")[0].get()

//        filter out data we want, may need more api calls


//        public MP(Integer govId, String name, String photoLink, String party, String constituencyName,
//              Integer constituencyId, String emailAddress, String twitter)
        MP newMp = new MP(id, name, thumbnailUrl, party,
                "placeholder", 0, "placeholder", "placeholder");
//        add using dao
        mpDAO.addMps(newMp);
    }

    public void createMpTable(){
        mpDAO.createMpTable();
    }

    public void dropMpTable(){
        mpDAO.dropMpTable();
    }

}
