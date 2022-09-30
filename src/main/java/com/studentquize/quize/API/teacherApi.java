package com.studentquize.quize.API;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import com.studentquize.quize.DB.studentDB;
import com.studentquize.quize.DB.MarkAndQuestionDB;
import com.studentquize.quize.DB.logDB;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class teacherApi {
    static final Logger logger = LoggerFactory.getLogger(teacherApi.class);

    @Autowired
    MarkAndQuestionDB markAndQuestionDB;
    @Autowired
    studentDB studentDb;
    @Autowired
    logDB logDb;

    @GetMapping(value = "/newTable")
    public String newTable() {

        logger.info("creating Question, student and report Tables");

        markAndQuestionDB.createQuestionTable();
        studentDb.createStudentTable();
        markAndQuestionDB.createReportTable();
        String log = "creating Question, student and report Tables";
        String url = "http://localhost:9192/rabbit/report";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, log, JSONObject.class);
        return "success";

    }

    @CacheEvict(value = "questions", allEntries = true)
    @GetMapping(value = "/addQ")
    public String insertQ() {
        logger.info("inserting question in Table (DEMO QUESTIONS)");
        markAndQuestionDB.addQuestionTable();
        String log = "inserting question in Table (DEMO QUESTIONS)";
        String url = "http://localhost:9192/rabbit/report";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, log, JSONObject.class);
        return "success";

    }

    @CacheEvict(value = "questions", allEntries = true)
    @GetMapping(value = "/insertQ")
    public String insertQT(@RequestBody List<JSONObject> question) {
        logger.info("inserting question in Table by teacher");
        markAndQuestionDB.addQuestionTeacher(question);
        String log = "inserting question in Table by teacher";
        String url = "http://localhost:9192/rabbit/report";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, log, JSONObject.class);
        return "success";

    }

    @CacheEvict(value = "questions", allEntries = true)
    @GetMapping(value = "/deleteQDB")
    public String deleteQDB() {
        logger.info("deleting the questions Table");
        markAndQuestionDB.deleteQuestionTable();
        String log = "deleting the questions Table";
        String url = "http://localhost:9192/rabbit/report";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, log, JSONObject.class);
        return "success";

    }

    @CacheEvict(value = "marks", allEntries = true)
    @GetMapping(value = "/deleteReport")
    public String deleteReport() {
        logger.info("deleting the report Table");
        markAndQuestionDB.deleteReportTable();
        String log = "deleting the report Table";
        String url = "http://localhost:9192/rabbit/report";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, log, JSONObject.class);
        return "success";

    }

    @Cacheable("marks")
    @GetMapping(value = "/showReport")
    public List<List<String>> showReport() {
        logger.info("showing the list of student marks");
        String log = "showing the list of student marks";
        String url = "http://localhost:9192/rabbit/report";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, log, JSONObject.class);
        return markAndQuestionDB.readReportTable();
    }

    @CacheEvict(value = "questions", allEntries = true)
    @GetMapping(value = "/updateQuestion")
    public String updateQuestion(@RequestBody JSONObject upQ) {
        logger.info("updating question {} where {}", upQ.get("update"), upQ.get("condition"));
        markAndQuestionDB.updateQ(upQ);
        String log = "updating question " + upQ.get("update") + " where " + upQ.get("condition");
        String url = "http://localhost:9192/rabbit/report";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, log, JSONObject.class);
        return "success";
    }

    @CacheEvict(value = "marks", allEntries = true)
    @GetMapping(value = "/updateMark")
    public String updateMark(@RequestBody JSONObject upMark) {
        logger.info("updating question {} where {}", upMark.get("update"), upMark.get("condition"));
        markAndQuestionDB.updateMark(upMark);
        String log = "updating question " + upMark.get("update") + " where " + upMark.get("condition");
        String url = "http://localhost:9192/rabbit/report";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, log, JSONObject.class);
        return "success";
    }

    @CacheEvict(value = "marks", allEntries = true)
    @GetMapping(value = "/deleteOneMark")
    public String deleteOneMark(@RequestBody String delMark) {
        logger.info("deleting mark where {}", delMark);
        markAndQuestionDB.delMark(delMark);
        String log = "deleting mark where " + delMark;
        String url = "http://localhost:9192/rabbit/report";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, log, JSONObject.class);
        return "success";

    }
}