package com.studentquize.quize;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class teacherAPI {
    @Autowired
    SomeComponent someComponent;

    //    TODO THE API
    @GetMapping(value = "/newdatabase")
    public void newdatabase() throws SQLException {
        someComponent.createQuestionDataBase();
    }

    //TODO nemidunm che kar konm felan
    @GetMapping(value = "/addQ")
    public void insertQ() throws SQLException {
        someComponent.addQuestionDataBase();
    }

    @GetMapping(value = "/insertQ")
    public void insertQ(@RequestBody List<JSONObject> question) throws SQLException {
        someComponent.addQuestionTeacher(question);
    }

    @GetMapping(value = "/deleteDB")
    public void deleteDB() throws SQLException {
        someComponent.deleteQuestionDataBase();
    }
}
