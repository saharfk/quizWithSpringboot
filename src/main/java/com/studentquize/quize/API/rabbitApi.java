package com.studentquize.quize.API;

import com.studentquize.quize.DB.logDB;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/rabbit")
public class rabbitApi {
    static final Logger logger = LoggerFactory.getLogger(markApi.class);
    @Autowired
    logDB logDb;

    @PatchMapping(value = "/logging")
    public String logging(@RequestBody String rabbitLog) {
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
        String url = "http://localhost:9192/rabbit/report";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, log, JSONObject.class);
        return "success";
    }
}
