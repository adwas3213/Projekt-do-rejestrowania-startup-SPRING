package com.example.registerstartupproject;

import com.example.registerstartupproject.adminPanel.BeginUsersSeed;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RegisterStartUpProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RegisterStartUpProjectApplication.class, args);
        context.getBean(BeginUsersSeed.class).createAccounts();
    }

}
