package com.studentquize.quize;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class apiCalls {
    @Autowired
    SomeComponent someComponent;

    //    TODO THE API
    @GetMapping(value = "/question")
    public JSONObject question() throws SQLException {
        JSONObject jsonObject = new JSONObject();
        someComponent.readQuestionDataBase();

//        //Inserting key-value pairs into the json object
//        jsonObject.put("code", 0);
//        jsonObject.put("msg", "");
//        jsonObject.put("status", 0);
        return jsonObject;

    }

    @GetMapping(value = "/checkanswer")
    public JSONObject checkanswer() {
        JSONObject jsonObject = new JSONObject();
        //Inserting key-value pairs into the json object
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("status", 0);
        return jsonObject;

    }

    @GetMapping(value = "/signUp")
    public JSONObject signUp() {
        JSONObject jsonObject = new JSONObject();
        //Inserting key-value pairs into the json object
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("status", 0);
        return jsonObject;

    }
//TODO calling API

    @GetMapping(value = "/countries")
    public List<Object> getcountries() {
        String url = "https://restcountries.eu/rest/v2/all";
        RestTemplate restTemplate = new RestTemplate();
        Object[] countries = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(countries);
    }

    //    you should call "http://localhost:8082/basicCall" in postman
    @GetMapping(value = "/basicCall")
    private List<Object> basicCall() {
        String url = "http://localhost:8081/api/user"; //calling mock api by
        RestTemplate restTemplate = new RestTemplate();
        JSONObject result = restTemplate.getForObject(url, JSONObject.class);
        return Arrays.asList(result);
    }

}
