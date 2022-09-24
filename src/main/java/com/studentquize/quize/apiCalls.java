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
import java.util.Objects;


@RestController
@RequestMapping("/api")
public class apiCalls {
    static final Logger logger = LoggerFactory.getLogger(apiCalls.class);

    @Autowired
    SomeComponent someComponent;

//    @Cacheable("questions")
    @GetMapping(value = "/questions")
    public JSONObject question() throws SQLException {
        logger.info("showing the list of questions");
        JSONObject jsonObject = someComponent.readQuestionTable();
        return jsonObject;
    }

//    @CacheEvict(value = "marks", allEntries = true)
    @GetMapping(value = "/checkanswer")
    public String checkanswer(@RequestBody List<String> answerInfo) throws SQLException {
        logger.info("checking the answers for adding marks");
        JSONObject jsonObject = someComponent.readQuestionAnswersTable();
        String[] words = answerInfo.get(1).split(",");
        if (words.length <= jsonObject.size()) {
            int point = 0;
            for (int i = 1; i <= words.length; i++) {
                System.out.println(jsonObject.get(String.valueOf(i)) + words[i - 1]);
                if (Objects.equals(words[i - 1], String.valueOf(jsonObject.get(String.valueOf(i))))) {
                    point += 5;
                }
            }
            someComponent.addReportTable(Integer.parseInt(answerInfo.get(0)), point);
            return "your grade is " + point;
        }
        return "you answered the wrong questions :)";
    }

}
