package com.studentquize.quize.DB;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//TODO CONFIG FOR CONNECTING TO DATABASE
@Component("AppConfig")
public class AppConfig {
    //    @Value("${jdbc.driverClassName}")
//    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String jdbcURL;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${app.db.port}")
    private String port;
    @Value("${app.db.servicename}")
    private String servicename;
    @Value("${rabbit.url}")
    private String rabbitUrl;

    @Override
    public String toString() {
        return "AppConfig{" +
                "jdbcURL='" + jdbcURL + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", port='" + port + '\'' +
                ", servicename='" + servicename + '\'' +
                ", rabbitUrl='" + rabbitUrl + '\'' +
                '}';
    }

    public String getRabbitUrl() {
        return rabbitUrl;
    }

    public void setRabbitUrl(String rabbitUrl) {
        this.rabbitUrl = rabbitUrl;
    }


    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    public void setJdbcURL(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
