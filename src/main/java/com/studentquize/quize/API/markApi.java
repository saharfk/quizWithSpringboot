package com.studentquize.quize.API;

import com.studentquize.quize.DB.MarkAndQuestionDB;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api")
public class markApi {
    static final Logger logger = LoggerFactory.getLogger(markApi.class);

    @Autowired
    MarkAndQuestionDB markAndQuestionDB;

    @Cacheable("questions")
    @GetMapping(value = "/questions")
    public JSONObject question() {
        logger.info("showing the list of questions");
        JSONObject jsonObject = markAndQuestionDB.readQuestionTable();
        String log = "showing the list of questions";
        String url = "http://172.18.63.37:9192/rabbit/report";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, log, JSONObject.class);
        return jsonObject;
    }

    @CacheEvict(value = "marks", allEntries = true)
    @GetMapping(value = "/checkanswer")
    public String checkanswer(@RequestBody List<String> answerInfo) {
        logger.info("checking the answers for adding marks");
        JSONObject jsonObject = markAndQuestionDB.readQuestionAnswersTable();
        String[] words = answerInfo.get(1).split(",");
        if (words.length == jsonObject.size()) {
            int point = 0;
            for (int i = 1; i <= words.length; i++) {
                if (Objects.equals(words[i - 1], String.valueOf(jsonObject.get(String.valueOf(i))))) {
                    point += 5;
                }
            }
            markAndQuestionDB.addReportTable(Integer.parseInt(answerInfo.get(0)), point);
            String log = "StdNum: " + answerInfo.get(0) + "'s grade is " + point;
            String url = "http://172.18.63.37:9192/rabbit/report";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(url, log, JSONObject.class);
            return "your grade is " + point;
        }
        return "you answered the wrong questions :)";
    }
}
