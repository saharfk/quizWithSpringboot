package com.studentquize.quize.DB;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class JDBCBean {
    static final Logger logger = LoggerFactory.getLogger(JDBCBean.class);

    @Autowired
    public AppConfig appConfig;

    @Bean
    public HikariDataSource mysqlDataSource() {
        logger.info("connecting to database");
        HikariConfig dataSource = new HikariConfig();
        dataSource.setJdbcUrl("jdbc:oracle:thin:@" + appConfig.getJdbcURL() + ":" + appConfig.getPort() + "/" + appConfig.getServicename());
        dataSource.setUsername(appConfig.getUsername());
        dataSource.setPassword(appConfig.getPassword());
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        return new HikariDataSource(dataSource);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }


}
