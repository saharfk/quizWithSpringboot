package com.studentquize.quize;

import com.zaxxer.hikari.HikariDataSource;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SomeComponent {
    public static final Logger logger = LoggerFactory.getLogger(SomeComponent.class);
    @Autowired
    HikariDataSource dataSource;

    public void createQuestionDataBase() throws SQLException {
        String sql = "CREATE TABLE questionTbl (\n" +
                "     id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,\n" +
                "    Questions varchar(255) NOT NULL,\n" +
                "    Answers varchar(1),\n" +
                "    QuestionOption varchar(255),\n" +
                "    PRIMARY KEY (id)\n" +
                ")";
        Connection con = dataSource.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.executeQuery();
    }

    public void createStudentDataBase() throws SQLException {
        String sql = "CREATE TABLE STUDENT (\n" +
                "     id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,\n" +
                "    studentName varchar(255) NOT NULL,\n" +
                "    studentNumber varchar(255)\n" +
                "    PRIMARY KEY (id)\n" +
                ")";
        Connection con = dataSource.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.executeQuery();
    }

    public void createReportDataBase() throws SQLException {
        String sql = "create table studentreport(\n" +
                "  id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,\n" +
                "  studentid int not null references STUDENT(ID),\n" +
                "  score int not null \n" +
                "  PRIMARY KEY (id)\n" +
                ")";
        Connection con = dataSource.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.executeQuery();
    }

    //    *************************************************
    public void addQuestionDataBase() throws SQLException {
        Connection con = dataSource.getConnection();
        String sql = "INSERT INTO QUESTIONTBL(Questions, QuestionOption, ANSWERS) VALUES('1. What is a correct syntax to output \"Hello World\" in Java?','1. Console.WriteLine(\"Hello World\");  2.echo(\"Hello World\"); 3. System.out.println(\"Hello World\");  4. print (\"Hello World\");','3')";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.executeQuery();
        sql = "INSERT INTO QUESTIONTBL(Questions, QuestionOption, ANSWERS) VALUES('2. Which data type is used to create a variable that should store text?','1. string  2. String  3. Txt  4. myString','2')";
        pst = con.prepareStatement(sql);
        pst.executeQuery();
        sql = "INSERT INTO QUESTIONTBL(Questions, QuestionOption, ANSWERS) VALUES('3. How do you create a variable with the numeric value 5?','1. x = 5;  2. int x = 5;  3. num x = 5  4. float x = 5;','2')";
        pst = con.prepareStatement(sql);
        pst.executeQuery();
        sql = "INSERT INTO QUESTIONTBL(Questions, QuestionOption, ANSWERS) VALUES('4. How do you create a variable with the floating number 2.8?','1. x = 2.8f;  2. byte x = 2.8f  3. int x = 2.8f;  4.float x = 2.8f;','4')";
        pst = con.prepareStatement(sql);
        pst.executeQuery();
        sql = "INSERT INTO QUESTIONTBL(Questions, QuestionOption, ANSWERS) VALUES('5. Which method can be used to find the length of a string?','1. getLength()  2. len()  3. length()  4. getSize()','3')";
        pst = con.prepareStatement(sql);
        pst.executeQuery();
        sql = "INSERT INTO QUESTIONTBL(Questions, QuestionOption, ANSWERS) VALUES('6. Which method can be used to return a string in upper case letters?','1. touppercase()  2. tuc()  3. upperCase()  4. toUpperCase()','4' )";
        pst = con.prepareStatement(sql);
        pst.executeQuery();
        sql = "INSERT INTO QUESTIONTBL(Questions, QuestionOption, ANSWERS) VALUES('7. How do you create a method in Java?','1. methodName()  2. methodName[]  3. methodName.  4. (methodName)','1')";
        pst = con.prepareStatement(sql);
        pst.executeQuery();
        sql = "INSERT INTO QUESTIONTBL(Questions, QuestionOption, ANSWERS) VALUES('8. Which operator can be used to compare two values?','1. <>  2. ==  3. =  4. ><','2' )";
        pst = con.prepareStatement(sql);
        pst.executeQuery();

//        }
    }

    public void addStudentDataBase(String studentName, String studentNumber) throws SQLException {
        String sql = "INSERT INTO STUDENT(studentName,studentNumber)\n" +
                "VALUES  (" + studentName + "," + studentNumber + ")";
        Connection con = dataSource.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.executeQuery();
    }

    //TODO momkene inja error bede
    public void addReportDataBase(int studentid, int score) throws SQLException {
        String sql = "INSERT INTO STUDENTREPORT(studentid,score)\n" +
                "VALUES (" + studentid + "," + score + ")";
        Connection con = dataSource.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.executeQuery();

    }

    //******************************************
    public void deleteQuestionDataBase() throws SQLException {
        String sql = "DROP TABLE QUESTIONTBL";
        Connection con = dataSource.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.executeQuery();

    }

    public void deleteStudentDataBase() throws SQLException {
        String sql = "DROP TABLE STUDENT";
        Connection con = dataSource.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.executeQuery();
    }

    public void deleteReportDataBase() throws SQLException {
        String sql = "DROP TABLE STUDENTREPORT";
        Connection con = dataSource.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.executeQuery();
    }

    //    **********************************************
    public JSONObject readQuestionDataBase() throws SQLException {
        JSONObject jsonObject = new JSONObject();
        String sql = "SELECT Questions, QuestionOption FROM QUESTIONTBL";
        Connection con = dataSource.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            jsonObject.put(rs.getString("Questions"), rs.getString("QuestionOption"));
        }
        return jsonObject;
    }
    public JSONObject readQuestionAnswersDataBase() throws SQLException {
        JSONObject jsonObject = new JSONObject();
        String sql = "SELECT Questions, ANSWERS FROM QUESTIONTBL";
        Connection con = dataSource.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            jsonObject.put(rs.getString("Questions"), rs.getString("ANSWERS"));
        }
        return jsonObject;
    }

    public void readStudentDataBase() throws SQLException {
        String sql = "SELECT * FROM STUDENT";
        Connection con = dataSource.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        System.out.println(rs);
        while (rs.next()) {
            int coffeeName = rs.getInt("Questions");
            int supplierID = rs.getInt("Answers");

            System.out.println(coffeeName + ", " + supplierID);
        }
    }

    public void readReportDataBase() throws SQLException {
        String sql = "SELECT * FROM STUDENTREPORT";
        Connection con = dataSource.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        System.out.println(rs);
        while (rs.next()) {
            int coffeeName = rs.getInt("Questions");
            int supplierID = rs.getInt("Answers");

            System.out.println(coffeeName + ", " + supplierID);
        }
    }
}