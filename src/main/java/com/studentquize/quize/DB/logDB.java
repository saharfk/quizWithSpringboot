package com.studentquize.quize.DB;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    AppConfig appConfig;

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
            String url = appConfig.getRabbitUrl();
            HttpEntity<String> request = new HttpEntity<>(log);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            System.out.println("response is : " + response);
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
            String url = appConfig.getRabbitUrl();
            HttpEntity<String> request = new HttpEntity<>(log);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            System.out.println("response is : " + response);
        }

    }

    public void deleteLogTable() {
        try {
            String sql = "DROP TABLE logTbl";
            Connection con = dataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeQuery();
        } catch (SQLException e) {
            logger.error("could not drop STUDENT table");
            String log = "ERROR: could not drop STUDENT table";
            String url = appConfig.getRabbitUrl();
            HttpEntity<String> request = new HttpEntity<>(log);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            System.out.println("response is : " + response);
        }

    }

}
