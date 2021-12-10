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

            countyDAO.addCountyConstituencies(county, arrayOfConstituencyIds);

        }



    }

    public void createCountyTable(){
        countyDAO.addCountyTable();
    }

    public void dropCountyTable(){
        countyDAO.dropCountyTable();
    }

    public void addDataNotInAPI(){
        HashMap<String, int[]> map1 = new HashMap<>();
        map1.put("Rhondda Cynon Taf", new int[]{3696, 3681, 3668, 3435});
        HashMap<String, int[]> map2 = new HashMap<>();
        map2.put("Worcestershire", new int[]{3848, 3609});
        HashMap<String, int[]> map3 = new HashMap<>();
        map3.put("Tyne and Wear", new int[]{3340, 3494, 3540,  3552,  3625, 3626, 3627,  3652,  3749,  3788, 3815,  3832});
        HashMap<String, int[]> map4 = new HashMap<>();
        map4.put("Rutland", new int[]{3712});
        HashMap<String, int[]> map5 = new HashMap<>();
        map5.put("Hertford", new int[]{3374, 3525, 3530, 3531, 3535, 3644,3756, 3765, 3773, 3833, 3839});
        HashMap<String, int[]> map6 = new HashMap<>();
        map6.put("Herefordshire", new int[]{3373,  3529, 3646,  3609, 3694, 3848, 3865, 3872});
        HashMap<String, int[]> map7 = new HashMap<>();
        map7.put("East Riding of Yorkshire", new int[]{3560, 3321, 3364, 3466, 3510});
        HashMap<String, int[]> map8 = new HashMap<>();
        map8.put("Bristol", new int[]{3370, 3367, 3368, 3369});
        ArrayList<HashMap<String, int[]>> listsOfConstituencyIds =
                new ArrayList<>(List.of(map1,map2,map3,map4,map5,map6,map7,map8));
        for (HashMap<String, int[]> county : listsOfConstituencyIds){
            Map.Entry<String,int[]> entry = county.entrySet().iterator().next();
            String countyName = entry.getKey();
            int[] constituencyIds = entry.getValue();
            countyDAO.addDataNotInAPI(countyName, constituencyIds);
        }
    }

}
