package com.capstone.proj.mp;

import com.capstone.proj.constituency.Constituency;
import com.capstone.proj.constituency.ConstituencyService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class MPService {

    private MPDAO mpDAO;
    private ConstituencyService constituencyService;

    @Autowired
    public MPService(MPDAO mpDAO, ConstituencyService constituencyService){
        this.mpDAO = mpDAO;
        this.constituencyService = constituencyService;
    }

    public void addMps(){
//        get constituency ids from the database
        List<Constituency> allConstituencies = constituencyService.getAllConstituencies();
//        loop thru and make a mp object for each constituency
        for (Constituency constituency : allConstituencies) {
            int constituencyId = constituency.getConstituency_id();
//        use api to get info on that constituency's mp
            RestTemplate restTemplate = new RestTemplate();
            String mpResourceUrl
                    = "https://members-api.parliament.uk/api/Members/Search?ConstituencyId=" + constituencyId + "&IsCurrentMember=true&skip=0&take=20";
            ResponseEntity<JsonNode> response
                    = restTemplate.getForEntity(mpResourceUrl, JsonNode.class);
            JsonNode responseObj = response.getBody();
            JsonNode mpResponse = responseObj.get("items").get(0);
//            for empty constituencies (no current mp)
            if (mpResponse == null) {
                mpDAO.addMps(new MP(null, null, null, null,
                        constituency.getConstituency_name(), constituencyId, null, null));
            } else {
                String name = String.valueOf(mpResponse.get("value").get("nameDisplayAs"));
                int id = mpResponse.get("value").get("id").intValue();
                String party = mpResponse.get("value").get("latestParty").get("name").textValue();
                String thumbnailUrl = mpResponse.get("value").get("thumbnailUrl").textValue();
                RestTemplate restTemplateContact = new RestTemplate();
                String mpContactUrl
                        = "https://members-api.parliament.uk/api/Members/" + id + "/Contact";
                ResponseEntity<JsonNode> responseContact
                        = restTemplateContact.getForEntity(mpContactUrl, JsonNode.class);
                JsonNode responseContactObj = responseContact.getBody().get("value");
                String email = "";
                String twitter = "";
                for (JsonNode obj : responseContactObj) {
                    if (obj.get("type").textValue().equals("Parliamentary")) {
                        email = String.valueOf(obj.get("email"));
                    } else if (obj.get("type").textValue().equals("Twitter")) {
                        twitter = String.valueOf(obj.get("line1"));
                    }
                }
                MP newMp = new MP(id, name.replaceAll("\"", ""), thumbnailUrl, party,
                        constituency.getConstituency_name(), constituencyId, email.replaceAll("\"", ""),
                        twitter.replaceAll("\"", ""));
//        add using dao
                mpDAO.addMps(newMp);
            }
        }
    }

    public void createMpTable(){
        mpDAO.createMpTable();
    }

    public void dropMpTable(){
        mpDAO.dropMpTable();
    }

    public Optional<MP> getMpByConstituencyId(int id) {
        return mpDAO.getMpByConstituencyId(id);
    }
}
