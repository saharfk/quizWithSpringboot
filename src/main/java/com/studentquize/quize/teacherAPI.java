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

    @GetMapping(value = "/newdatabase")
    public void newdatabase() throws SQLException {

        logger.info("[] creating Question, student and report databases");

        someComponent.createQuestionDataBase();
        someComponent.createStudentDataBase();
        someComponent.createReportDataBase();
    }

//    @CacheEvict(value = "questions", allEntries = true)
    @GetMapping(value = "/addQ")
    public void insertQ() throws SQLException {
        logger.info("[] inserting question in database (DEMO QUESTIONS)");
        someComponent.addQuestionDataBase();
    }

    //    @CacheEvict(value = "questions", allEntries = true)
    @GetMapping(value = "/insertQ")
    public void insertQT(@RequestBody List<JSONObject> question) throws SQLException {
        logger.info("[] inserting question in database by teacher");
        someComponent.addQuestionTeacher(question);
    }

    //    @CacheEvict(value = "questions", allEntries = true)
    @GetMapping(value = "/deleteQDB")
    public void deleteQDB() throws SQLException {
        logger.info("[] deleting the questions database");
        someComponent.deleteQuestionDataBase();
    }

    //    @CacheEvict(value = "marks", allEntries = true)
    @GetMapping(value = "/deleteReport")
    public void deleteReport() throws SQLException {
        logger.info("[] deleting the report database");
        someComponent.deleteReportDataBase();
    }

    //    @Cacheable("marks")
    @GetMapping(value = "/showReport")
    public List<List<String>> showReport() throws SQLException {
        logger.info("[] showing the list of student marks");
        return someComponent.readReportDataBase();
    }

    //    @CacheEvict(value = "questions", allEntries = true)
    @GetMapping(value = "/updateQuestion")
    public void updateQuestion(@RequestBody JSONObject upQ) throws SQLException {
        logger.info("[] updating question {} where {}", upQ.get("update"), upQ.get("condition"));
        someComponent.updateQ(upQ);
    }

    //    @CacheEvict(value = "marks", allEntries = true)
    @GetMapping(value = "/updateMark")
    public void updateMark(@RequestBody JSONObject upMark) throws SQLException {
        logger.info("[] updating question {} where {}", upMark.get("update"), upMark.get("condition"));
        someComponent.updateMark(upMark);
    }

    //    @CacheEvict(value = "marks", allEntries = true)
    @GetMapping(value = "/deleteOneMark")
    public void deleteOneMark(@RequestBody JSONObject delMark) throws SQLException {
        logger.info("[] deleting mark where {}", delMark.get("condition"));
        someComponent.delMark(delMark);
    }
}
