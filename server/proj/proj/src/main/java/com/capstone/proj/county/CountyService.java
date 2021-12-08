package com.capstone.proj.county;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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

    public void addAllCountyNames(){
        ArrayList<String> listOfNames = new ArrayList<>(Arrays.asList("Bedfordshire",
                "Berkshire",
                "Bristol",
                "Buckinghamshire",
                "Cambridgeshire",
                "Cheshire",
                "City of London",
                "Cornwall",
                "Cumbria",
                "Derbyshire",
                "Devon",
                "Dorset",
                "Durham",
                "East Riding of Yorkshire",
                "East Sussex",
                "Essex",
                "Gloucestershire",
                "Greater London",
                "Greater Manchester",
                "Hampshire",
                "Herefordshire",
                "Hertford",
                "Isle of Wight",
                "Kent",
                "Lancashire",
                "Leicestershire",
                "Lincolnshire",
                "Merseyside",
                "Norfolk",
                "North Yorkshire",
                "Northamptonshire",
                "Northumberland",
                "Nottinghamshire",
                "Oxfordshire",
                "Rutland",
                "Shropshire",
                "Somerset",
                "South Yorkshire",
                "Staffordshire",
                "Suffolk",
                "Surrey",
                "Tyne and Wear",
                "Warwickshire",
                "West Midlands",
                "West Sussex",
                "West Yorkshire",
                "Wiltshire",
                "Worcestershire",

                "Isle of Anglesey",
                "Gwynedd",
                "Conwy",
                "Denbighshire",
                "Flintshire",
                "Wrexham",
                "Powys",
                "Ceredigion",
                "Carmarthenshire",
                "Pembrokeshire",
                "Swansea",
                "Neath Port Talbot",
                "Bridgend",
                "The Vale of Glamorgan",
                "Rhondda Cynon Taf",
                "Cardiff",
                "Caerphilly",
                "Merthyr Tydfil",
                "Blaenau Gwent",
                "Torfaen",
                "Newport",
                "Monmouthshire"
        ));
        countyDAO.addAllCountyNames(listOfNames);
    }

    public void addCountyConstituencies(){
        ArrayList<String> listOfCounties = new ArrayList<>(Arrays.asList("Bedfordshire",
                "Berkshire",
                "Bristol",
                "Buckinghamshire",
                "Cambridgeshire",
                "Cheshire",
                "City of London",
                "Cornwall",
                "Cumbria",
                "Derbyshire",
                "Devon",
                "Dorset",
                "Durham",
                "East Riding of Yorkshire",
                "East Sussex",
                "Essex",
                "Gloucestershire",
                "Greater London",
                "Greater Manchester",
                "Hampshire",
                "Herefordshire",
                "Hertford",
                "Isle of Wight",
                "Kent",
                "Lancashire",
                "Leicestershire",
                "Lincolnshire",
                "Merseyside",
                "Norfolk",
                "North Yorkshire",
                "Northamptonshire",
                "Northumberland",
                "Nottinghamshire",
                "Oxfordshire",
                "Rutland",
                "Shropshire",
                "Somerset",
                "South Yorkshire",
                "Staffordshire",
                "Suffolk",
                "Surrey",
                "Tyne and Wear",
                "Warwickshire",
                "West Midlands",
                "West Sussex",
                "West Yorkshire",
                "Wiltshire",
                "Worcestershire",
                "Isle of Anglesey",
                "Gwynedd",
                "Conwy",
                "Denbighshire",
                "Flintshire",
                "Wrexham",
                "Powys",
                "Ceredigion",
                "Carmarthenshire",
                "Pembrokeshire",
                "Swansea",
                "Neath Port Talbot",
                "Bridgend",
                "The Vale of Glamorgan",
                "Rhondda Cynon Taf",
                "Cardiff",
                "Caerphilly",
                "Merthyr Tydfil",
                "Blaenau Gwent",
                "Torfaen",
                "Newport",
                "Monmouthshire"
        ));
//        get from api
        RestTemplate restTemplate = new RestTemplate();
        for (String county: listOfCounties) {
            ArrayList<Integer> listOfConstituencyIds = new ArrayList<>();
            String fooResourceUrl
                    = "https://members-api.parliament.uk/api/Location/Browse/1/" + county;
            ResponseEntity<JsonNode> response
                    = restTemplate.getForEntity(fooResourceUrl, JsonNode.class);
            JsonNode responseObj = response.getBody();

//            this is a list need to get id from each one
            JsonNode constituencyList = responseObj.get("value").get("childContexts");
            for (JsonNode constituency : constituencyList){
                listOfConstituencyIds.add(constituency.get("id").intValue());
            }

            int [] arrayOfConstituencyIds = listOfConstituencyIds.stream().mapToInt(i -> i).toArray();
            //        call dao to add to db

            System.out.println(Arrays.toString(arrayOfConstituencyIds) + " " + county);

            countyDAO.addCountyConstituencies(county, arrayOfConstituencyIds);

        }



    }

}
