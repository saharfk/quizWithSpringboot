package com.studentquize.quize.API;

import com.studentquize.quize.DB.AppConfig;
import com.studentquize.quize.DB.studentDB;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/student")
public class studentApi {
    static final Logger logger = LoggerFactory.getLogger(studentApi.class);

    @Autowired
    studentDB studentDb;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    public AppConfig appConfig;

    //    TODO THE API
    @CacheEvict(value = "students", allEntries = true)
    @GetMapping(value = "/newStudentDB")
    public String newStudentDB() {
        logger.info("creating new student Table");
        studentDb.createStudentTable();
        String log = "creating new student Table";
        String url = appConfig.getRabbitUrl();
        HttpEntity<String> request = new HttpEntity<>(log);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        System.out.println("response is : " + response);

        return "success";

    }

    //TODO nemidunm che kar konm felan
    @CacheEvict(value = "students", allEntries = true)
    @GetMapping(value = "/signUp")
    public String signUp(@RequestBody List<String> studentInfo) {
        logger.info("adding student to Table");
        studentDb.addStudentTable(Integer.parseInt(studentInfo.get(0)), studentInfo.get(1), studentInfo.get(2));
        String log = "adding student to Table";
        String url = appConfig.getRabbitUrl();
        HttpEntity<String> request = new HttpEntity<>(log);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        System.out.println("response is : " + response);

        return "success";
    }

    @CacheEvict(value = "students", allEntries = true)
    @GetMapping(value = "/delete")
    public String delete() {
        logger.info("deleting student Table");
        studentDb.deleteStudentTable();
        String log = "deleting student Table";
        String url = appConfig.getRabbitUrl();
        HttpEntity<String> request = new HttpEntity<>(log);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        System.out.println("response is : " + response);

        return "success";
    }

    @CacheEvict(value = "students", allEntries = true)
    @GetMapping(value = "/deleteSt")
    public String deleteSt(@RequestBody int num) {
        logger.info("deleting studentId {}", num);
        studentDb.deleteStudent(num);
        String log = "deleting studentId " + num;
        String url = appConfig.getRabbitUrl();
        HttpEntity<String> request = new HttpEntity<>(log);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        System.out.println("response is : " + response);

        return "success";

    }

    @CacheEvict(value = "students", allEntries = true)
    @GetMapping(value = "/updateSt")
    public String updateSt(@RequestBody JSONObject upSt) {
        logger.info("updating student where {}", upSt.get("update"));
        studentDb.updateStudent(upSt);
        String log = "updating student where " + upSt.get("update");
        String url = appConfig.getRabbitUrl();
        HttpEntity<String> request = new HttpEntity<>(log);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        System.out.println("response is : " + response);

        return "success";
    }

    @Cacheable("students")
    @GetMapping(value = "/studentList")
    public List<List<String>> studentList() {
        logger.info("showing the list of students");
        String log = "showing the list of students";
        String url = appConfig.getRabbitUrl();
        HttpEntity<String> request = new HttpEntity<>(log);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        System.out.println("response is : " + response);

        return studentDb.readStudentTable();

    }
}
