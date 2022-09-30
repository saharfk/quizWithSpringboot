package com.studentquize.quize.API;

import com.studentquize.quize.DB.logDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbit")
public class rabbitApi {
    static final Logger logger = LoggerFactory.getLogger(markApi.class);
    @Autowired
    logDB logDb;

    @CacheEvict(value = "rabbit", allEntries = true)
    @PatchMapping(value = "/logging")
    public String logging(@RequestBody String rabbitLog) {
        logger.info("log to table");
        logDb.addLogTable(rabbitLog);
        return "success";
    }

    @CacheEvict(value = "rabbit", allEntries = true)
    @GetMapping(value = "/logCreate")
    public String logCreate() {
        logger.info("create log table");
        logDb.LogTable();
        return "success";
    }
}
