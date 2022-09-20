package com.studentquize.quize;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JDBCBean {
    @Autowired
    public AppConfig appConfig;

    @Bean
    public HikariDataSource mysqlDataSource() {
        HikariConfig dataSource = new HikariConfig();
        dataSource.setJdbcUrl("jdbc:oracle:thin:@" + appConfig.getJdbcURL() + ":" + appConfig.getPort() + "/" + appConfig.getServicename());
        dataSource.setUsername(appConfig.getUsername());
        dataSource.setPassword(appConfig.getPassword());
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        return new HikariDataSource(dataSource);
    }


}
