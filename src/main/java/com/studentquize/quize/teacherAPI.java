package com.studentquize.quize;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class teacherAPI {
    static final Logger logger = LoggerFactory.getLogger(teacherAPI.class);

    @Autowired
    SomeComponent someComponent;

    @GetMapping(value = "/newTable")
    public String newTable() {

        logger.info("creating Question, student and report Tables");

        someComponent.createQuestionTable();
        someComponent.createStudentTable();
        someComponent.createReportTable();
        return "success";

    }

    @CacheEvict(value = "questions", allEntries = true)
    @GetMapping(value = "/addQ")
    public String insertQ(){
        logger.info("inserting question in Table (DEMO QUESTIONS)");
        someComponent.addQuestionTable();
        return "success";

    }

    @CacheEvict(value = "questions", allEntries = true)
    @GetMapping(value = "/insertQ")
    public String insertQT(@RequestBody List<JSONObject> question){
        logger.info("inserting question in Table by teacher");
        someComponent.addQuestionTeacher(question);
        return "success";

    }

    @CacheEvict(value = "questions", allEntries = true)
    @GetMapping(value = "/deleteQDB")
    public String deleteQDB(){
        logger.info("deleting the questions Table");
        someComponent.deleteQuestionTable();
        return "success";

    }

    @CacheEvict(value = "marks", allEntries = true)
    @GetMapping(value = "/deleteReport")
    public String deleteReport(){
        logger.info("deleting the report Table");
        someComponent.deleteReportTable();
        return "success";

    }

    @Cacheable("marks")
    @GetMapping(value = "/showReport")
    public List<List<String>> showReport() {
        logger.info("showing the list of student marks");
        return someComponent.readReportTable();
    }

    @CacheEvict(value = "questions", allEntries = true)
    @GetMapping(value = "/updateQuestion")
    public String updateQuestion(@RequestBody JSONObject upQ) {
        logger.info("updating question {} where {}", upQ.get("update"), upQ.get("condition"));
        someComponent.updateQ(upQ);
        return "success";
    }

    @CacheEvict(value = "marks", allEntries = true)
    @GetMapping(value = "/updateMark")
    public String updateMark(@RequestBody JSONObject upMark) {
        logger.info("updating question {} where {}", upMark.get("update"), upMark.get("condition"));
        someComponent.updateMark(upMark);
        return "success";
    }

    @CacheEvict(value = "marks", allEntries = true)
    @GetMapping(value = "/deleteOneMark")
    public String deleteOneMark(@RequestBody String delMark) {
        logger.info("[] deleting mark where {}", delMark);
        someComponent.delMark(delMark);
        return "success";

    }
}
