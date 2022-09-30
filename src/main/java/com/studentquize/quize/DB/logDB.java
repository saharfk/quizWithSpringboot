package com.studentquize.quize.DB;

import com.zaxxer.hikari.HikariDataSource;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component

public class logDB {
    public static final Logger logger = LoggerFactory.getLogger(MarkAndQuestionDB.class);
    @Autowired
    HikariDataSource dataSource;

    public void LogTable() {
        try {
            String sql = "CREATE TABLE logTbl (\n" +
                    "     id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,\n" +
                    "    log varchar(255) NOT NULL,\n" +
                    "    PRIMARY KEY (id)\n" +
                    ")";
            Connection con = dataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeQuery();

        } catch (SQLException e) {
            logger.error("could not creat the QUESTION table");
            String log = "ERROR: could not creat the QUESTION table";
            String url = "http://localhost:9192/rabbit/report";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(url, log, JSONObject.class);
        }

    }

    public void addLogTable(String rabbitLog) {
        try {
            String sql = "INSERT INTO logTbl(log)\n" +
                    "VALUES (" + rabbitLog + ")";

            Connection con = dataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeQuery();

        } catch (SQLException e) {
            logger.error("could not creat the log table");
            String log = "ERROR: could not creat the log table";
            String url = "http://localhost:9192/rabbit/report";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(url, log, JSONObject.class);
        }

    }

}
