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

    public List<Map<String, String>> getRadarData() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        URI url= URI.create(createRadarUrl());
        ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(Objects.requireNonNull(response.getBody()).toString());
        JsonObject item = (JsonObject) jsonObject.get("response").getAsJsonObject()
                .get("body").getAsJsonObject()
                .get("items").getAsJsonObject()
                .get("item").getAsJsonArray()
                .get(0);
        List<String> list = Arrays.asList(item.get("rdr-img-file").getAsString().replace("\"", "").replace("[", "").replace("]", "").split(","));
        List<Map<String, String>> radarData = new ArrayList<>();

        Collections.reverse(list);
        int count = 1;
        for(int i=0; i<list.size(); i+=12) {
            if(count == 11) {
                break;
            }else {
                count++;
                Map<String, String> radarNameUrls = new HashMap<>();
                radarNameUrls.put("name", list.get(i).substring(list.get(i).length() - 16 , list.get(i).length() - 4));
                radarNameUrls.put("url", list.get(i));
                radarData.add(radarNameUrls);
            }
        }

        return radarData;
    }

    private String createRadarUrl() {
        String pageUrl = "http://apis.data.go.kr/1360000/RadarImgInfoService/getCmpImg";
        String serviceKey = "7ybMtm52Wvh5bOH8PhzGTk%2Bramhq1PsDZgL5B0dstTb%2FcqiNGoCYylggaTrr1iWyDjCj5K5ux6Gr1eYIwmol9g%3D%3D";
        int pageNo = 1;
        int numOfRows = 10;
        String dataType = "JSON";
        String data = "CMP_WRC";
        String time = "20210406";
        String queryParams = "?ServiceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&dataType=" + dataType + "&data=" + data + "&time=" + time;

        return pageUrl + queryParams;
    }

    public ImmutableBiMap<String, Object> dataProcess() {
        return null;
    }
}
