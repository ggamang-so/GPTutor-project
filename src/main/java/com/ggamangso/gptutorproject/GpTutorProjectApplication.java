package com.ggamangso.gptutorproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class GpTutorProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpTutorProjectApplication.class, args);
    }

}
