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
@RequestMapping("/student")
public class studentApi {
    static final Logger logger = LoggerFactory.getLogger(studentApi.class);

    @Autowired
    SomeComponent someComponent;

    //    TODO THE API
    @CacheEvict(value = "students", allEntries = true)
    @GetMapping(value = "/newStudentDB")
    public String newStudentDB() throws SQLException {
        logger.info("creating new student Table");
        someComponent.createStudentTable();
        return "success";

    }

    //TODO nemidunm che kar konm felan
    @CacheEvict(value = "students", allEntries = true)
    @GetMapping(value = "/signUp")
    public String signUp(@RequestBody List<String> studentInfo) throws SQLException {
        logger.info("adding student to Table");
        someComponent.addStudentTable(Integer.parseInt(studentInfo.get(0)), studentInfo.get(1), studentInfo.get(2));
        return "success";
    }

    @CacheEvict(value = "students", allEntries = true)
    @GetMapping(value = "/delete")
    public String delete() throws SQLException {
        logger.info("deleting student Table");
        someComponent.deleteStudentTable();
        return "success";
    }

    @CacheEvict(value = "students", allEntries = true)
    @GetMapping(value = "/deleteSt")
    public String deleteSt(@RequestBody int num) throws SQLException {
        logger.info("deleting studentId {}", num);
        someComponent.deleteStudent(num);
        return "success";

    }

    @CacheEvict(value = "students", allEntries = true)
    @GetMapping(value = "/updateSt")
    public String updateSt(@RequestBody JSONObject upSt) throws SQLException {
        logger.info("updating student where {}", upSt.get("update"));
        someComponent.updateStudent(upSt);
        return "success";
    }

    @Cacheable("students")
    @GetMapping(value = "/studentList")
    public List<List<String>> studentList() throws SQLException {
        logger.info("showing the list of students");
        return someComponent.readStudentTable();

    }
}
