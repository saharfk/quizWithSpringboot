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
    public void newStudentDB() throws SQLException {
        someComponent.createStudentDataBase();
    }

    //TODO nemidunm che kar konm felan
    @CacheEvict(value = "students", allEntries = true)
    @GetMapping(value = "/signUp")
    public void signUp(@RequestBody List<String> studentInfo) throws SQLException {
        someComponent.addStudentDataBase(Integer.parseInt(studentInfo.get(0)), studentInfo.get(1), studentInfo.get(2));
    }

    @CacheEvict(value = "students", allEntries = true)
    @GetMapping(value = "/delete")
    public void delete() throws SQLException {
        someComponent.deleteStudentDataBase();
    }

    @CacheEvict(value = "students", allEntries = true)
    @GetMapping(value = "/deleteSt")
    public void deleteSt(@RequestBody int num) throws SQLException {
        someComponent.deleteStudent(num);
    }

    @CacheEvict(value = "students", allEntries = true)
    @GetMapping(value = "/updateSt")
    public void updateSt(@RequestBody JSONObject upSt) throws SQLException {
        someComponent.updateStudent(upSt);
    }

    @Cacheable("students")
    @GetMapping(value = "/studentList")
    public List<List<String>> studentList() throws SQLException {
        return someComponent.readStudentDataBase();

    }
}
