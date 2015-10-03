package com.schultzco.webservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.schultzco.webservices"})
@SpringBootApplication
public class SchultzCoServicesApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SchultzCoServicesApplication.class, args);
    }

    /**
     *  This allows the war file to deploy directly into tomcat.
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SchultzCoServicesApplication.class);
    }
    
}
