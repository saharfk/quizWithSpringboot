package com.studentquize.quize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class QuizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizeApplication.class, args);
    }

}
