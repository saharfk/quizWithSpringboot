package com.studentquize.quize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/student")
public class studentApi {
    @Autowired
    SomeComponent someComponent;

    //    TODO THE API
    @GetMapping(value = "/newStudentDB")
    public void newStudentDB() throws SQLException {
        someComponent.createStudentDataBase();
    }

    //TODO nemidunm che kar konm felan
    @GetMapping(value = "/signUp")
    public void signUp(@RequestBody List<String> studentInfo) throws SQLException {
        someComponent.addStudentDataBase(Integer.parseInt(studentInfo.get(0)), studentInfo.get(1), studentInfo.get(2));
    }

    @GetMapping(value = "/delete")
    public void delete() throws SQLException {
        someComponent.deleteStudentDataBase();
    }

    @GetMapping(value = "/studentList")
    public List<List<String>> studentList() throws SQLException {
        return someComponent.readStudentDataBase();

    }
}
