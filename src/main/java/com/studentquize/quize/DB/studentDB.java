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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class studentDB {
    public static final Logger logger = LoggerFactory.getLogger(MarkAndQuestionDB.class);
    @Autowired
    HikariDataSource dataSource;

//*******************************CREATE*****************************************

    public void createStudentTable() {
        try {
            String sql = " CREATE TABLE STUDENT (\n" +
                    "     studentId NUMBER NOT NULL,\n" +
                    "     studentName varchar(255) NOT NULL,\n" +
                    "     studentNumber varchar(255),\n" +
                    "     PRIMARY KEY (studentId))";
            Connection con = dataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeQuery();
        } catch (SQLException e) {
            logger.error("could not creat the STUDENT table");
            String log = "ERROR: could not creat the STUDENT table";
            String url = "http://localhost:9192/rabbit/report";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(url, log, JSONObject.class);
        }

    }
//*******************************ADD*****************************************

    public void addStudentTable(int studentId, String studentName, String studentNumber) {
        try {
            String sql = "INSERT INTO STUDENT(studentId,studentName,studentNumber)\n" +
                    "VALUES  (" + studentId + ", '" + studentName + "', '" + studentNumber + "')";
            Connection con = dataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeQuery();
        } catch (SQLException e) {
            logger.error("could not insert values in STUDENT table");
            String log = "ERROR: could not insert values in STUDENT table";
            String url = "http://localhost:9192/rabbit/report";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(url, log, JSONObject.class);
        }

    }
//*******************************DELETE*****************************************

    public void deleteStudentTable() {
        try {
            String sql = "DROP TABLE STUDENT";
            Connection con = dataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeQuery();
        } catch (SQLException e) {
            logger.error("could not drop STUDENT table");
            String log = "ERROR: could not drop STUDENT table";
            String url = "http://localhost:9192/rabbit/report";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(url, log, JSONObject.class);
        }

    }

    public void deleteStudent(int num) {
        try {
            String sql = "DELETE\n" +
                    "FROM\n" +
                    "    STUDENT\n" +
                    "WHERE\n" +
                    "    STUDENTID = " + num;
            Connection con = dataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeQuery();
        } catch (SQLException e) {
            logger.error("could not delete STUDENT number {} from table", num);
            String log = "ERROR:could not delete STUDENT number " + num + " from table";
            String url = "http://localhost:9192/rabbit/report";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(url, log, JSONObject.class);
        }

    }
//*******************************READ*****************************************

    public List<List<String>> readStudentTable() {
        List<List<String>> data = new ArrayList<>();
        try {
            String sql = "SELECT * FROM STUDENT";
            Connection con = dataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                List<String> innerData = new ArrayList<>();
                int studentId = rs.getInt("studentId");
                String studentName = rs.getString("studentName");
                int studentNumber = rs.getInt("studentNumber");
                innerData.add(String.valueOf(studentId));
                innerData.add(studentName);
                innerData.add(String.valueOf(studentNumber));
                data.add(innerData);
            }
            return data;
        } catch (SQLException e) {
            logger.error("could not read the student table");
            String log = "ERROR: could not read the student table";
            String url = "http://localhost:9192/rabbit/report";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(url, log, JSONObject.class);
            return data;
        }

    }
//*******************************UPDATE*****************************************

    public void updateStudent(JSONObject upSt) {
        try {
            String sql = "UPDATE  STUDENT SET " + upSt.get("update") + " WHERE " + upSt.get("condition");
            Connection con = dataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeQuery();
        } catch (SQLException e) {
            logger.error("could not read the student table");
            String log = "ERROR: could not read the student table";
            String url = "http://localhost:9192/rabbit/report";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(url, log, JSONObject.class);

        }

    }

}
