package com.studentquize.quize.API;

import com.studentquize.quize.DB.AppConfig;
import com.studentquize.quize.DB.logDB;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/rabbit")
public class rabbitApi {
    static final Logger logger = LoggerFactory.getLogger(markApi.class);
    @Autowired
    logDB logDb;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    public AppConfig appConfig;

    @PostMapping(value = "/logging")
    public String logging(@RequestBody String rabbitLog) {
        System.out.println("wtf");
        logger.info("log to table");
        logDb.addLogTable(rabbitLog);
        return "success";
    }

    @GetMapping(value = "/logCreate")
    public String logCreate() {
        logger.info("create log table");
        logDb.LogTable();
        return "success";
    }

    @GetMapping(value = "/deleteLog")
    public String deleteLog() {
        logger.info("deleting log Table");
        logDb.deleteLogTable();
        String log = "deleting log Table";
        String url = appConfig.getRabbitUrl();
        HttpEntity<String> request = new HttpEntity<>(log);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        System.out.println("response is : " + response);
        return "success";
    }
}
