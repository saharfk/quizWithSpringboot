package com.studentquize.quize;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class apiCalls {
    @Autowired
    SomeComponent someComponent;

    //    TODO THE API
    @GetMapping(value = "/question")
    public JSONObject question() throws SQLException {
        JSONObject jsonObject = someComponent.readQuestionDataBase();
        return jsonObject;
    }
//TODO nemidunm che kar konm felan
    @GetMapping(value = "/checkanswer")
    public JSONObject checkanswer(@RequestBody String answer) throws SQLException {
        JSONObject jsonObject = someComponent.readQuestionAnswersDataBase();
        String[] words=answer.split(",");
//        for


        return jsonObject;

    }

    @GetMapping(value = "/signUp")
    public void signUp(@RequestBody String studentInfo) throws SQLException {
        String[] information= studentInfo.split(",");
//        student name and student number
        someComponent.addStudentDataBase(information[0],information[1]);
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
