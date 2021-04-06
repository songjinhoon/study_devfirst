package com.devfirst.admin.epic.service;

import com.google.common.collect.ImmutableBiMap;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@RequiredArgsConstructor
@Service
public class DashboardService {

    public List<String> getRadarData() throws Exception {
        String pageUrl = "http://apis.data.go.kr/1360000/RadarImgInfoService/getCmpImg";
	    String serviceKey = "7ybMtm52Wvh5bOH8PhzGTk%2Bramhq1PsDZgL5B0dstTb%2FcqiNGoCYylggaTrr1iWyDjCj5K5ux6Gr1eYIwmol9g%3D%3D";
	    int pageNo = 1;
	    int numOfRows = 10;
	    String dataType = "JSON";
	    String data = "CMP_WRC";
	    String time = "20210406";
        String queryParams = "?ServiceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&dataType=" + dataType + "&data=" + data + "&time=" + time;
        String requestUrl = pageUrl + queryParams;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity("parameters", headers);
        URI url= URI.create(requestUrl);
        ResponseEntity response= restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonArray = (JsonObject) jsonParser.parse(Objects.requireNonNull(response.getBody()).toString());
        JsonObject item = (JsonObject) jsonArray.get("response").getAsJsonObject().get("body").getAsJsonObject().get("items").getAsJsonObject().get("item").getAsJsonArray().get(0);
        System.out.println(item.get("rdr-img-file").getAsString().replace("\"", ""));
        List<String> list = Arrays.asList(item.get("rdr-img-file").getAsString().replace("\"", "").replace("[", "").replace("]", "").split(","));
        List<String> radarUrls = new ArrayList<>();
        Collections.reverse(list);
        int count = 1;
        for(int i=0; i<list.size(); i+=12) {
            if(count == 11) {
                break;
            }else {
                count++;
                radarUrls.add(list.get(i));
            }
        }
        System.out.println(radarUrls.size());

        return radarUrls;
    }

    public ImmutableBiMap<String, Object> dataProcess() {
        return null;
    }
}
