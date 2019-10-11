package com.bbawker.webservice.ETOAdmin;

import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/easyAdmin")
public class ApiETOAdminController {

    @PostMapping("/subwayInfo")
    public Map<String, String> httpGetSubwayInfo(@RequestBody Map<String, Object> params) {
        Map<String, String> map = new HashMap<String, String>();
        BufferedReader br = null;
        try{
            String urlstr = "http://openapi.seoul.go.kr:8088/"
                    + "45556961536672693933414d546c6c/json/SearchSTNBySubwayLineInfo/1/1000/%20/%20/"+params.get("line");
            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("GET");
            System.out.println(urlconnection);
            br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
            String result = "";
            String line2;
            while((line2 = br.readLine()) != null) {
                result = result + line2 + "\n";
            }

            map.put("result", result);
            System.out.println(result);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return map;
    }

}
