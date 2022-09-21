package com.studentquize.quize;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api")
public class apiCalls {
    @Autowired
    SomeComponent someComponent;

    //    TODO THE API
    @GetMapping(value = "/questions")
    public JSONObject question() throws SQLException {
        JSONObject jsonObject = someComponent.readQuestionDataBase();
        return jsonObject;
    }

    //TODO nemidunm che kar konm felan
    @GetMapping(value = "/checkanswer")
    public String checkanswer(@RequestBody List<String> answerInfo) throws SQLException {
        JSONObject jsonObject = someComponent.readQuestionAnswersDataBase();
        String[] words = answerInfo.get(1).split(",");
        if (words.length > jsonObject.size()) {
            int point = 0;
            for (int i = 1; i <= words.length; i++) {
                System.out.println(jsonObject.get(String.valueOf(i)) + words[i - 1]);
                if (Objects.equals(words[i - 1], String.valueOf(jsonObject.get(String.valueOf(i))))) {
                    point += 5;
                }
            }
            someComponent.addReportDataBase(Integer.parseInt(answerInfo.get(0)), point);
            return "your grade is " + point;
        }
        return "you answered the wrong questions :)";
    }

}
