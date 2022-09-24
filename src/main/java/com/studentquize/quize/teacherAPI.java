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

    //    TODO THE API
    @GetMapping(value = "/newdatabase")
    public void newdatabase() throws SQLException {
        someComponent.createQuestionDataBase();
        someComponent.createStudentDataBase();
        someComponent.createReportDataBase();
    }

    //TODO nemidunm che kar konm felan
    @CacheEvict(value = "questions", allEntries = true)
    @GetMapping(value = "/addQ")
    public void insertQ() throws SQLException {
        someComponent.addQuestionDataBase();
    }

    @CacheEvict(value = "questions", allEntries = true)
    @GetMapping(value = "/insertQ")
    public void insertQ(@RequestBody List<JSONObject> question) throws SQLException {
        someComponent.addQuestionTeacher(question);
    }

    @CacheEvict(value = "questions", allEntries = true)
    @GetMapping(value = "/deleteQDB")
    public void deleteQDB() throws SQLException {
        someComponent.deleteQuestionDataBase();
    }

    @CacheEvict(value = "marks", allEntries = true)
    @GetMapping(value = "/deleteReport")
    public void deleteReport() throws SQLException {
        someComponent.deleteReportDataBase();
    }

    @Cacheable("marks")
    @GetMapping(value = "/showReport")
    public List<List<String>> showReport() throws SQLException {
        return someComponent.readReportDataBase();
    }

    @CacheEvict(value = "questions", allEntries = true)
    @GetMapping(value = "/updateQuestion")
    public void updateQuestion(@RequestBody JSONObject upQ) throws SQLException {
        someComponent.updateQ(upQ);
    }

    @CacheEvict(value = "marks", allEntries = true)
    @GetMapping(value = "/updateMark")
    public void updateMark(@RequestBody JSONObject upMark) throws SQLException {
        someComponent.updateMark(upMark);
    }

    @CacheEvict(value = "marks", allEntries = true)
    @GetMapping(value = "/deleteOneMark")
    public void deleteOneMark(@RequestBody JSONObject delMark) throws SQLException {
        someComponent.delMark(delMark);
    }
}
