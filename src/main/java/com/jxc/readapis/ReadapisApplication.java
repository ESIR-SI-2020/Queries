package com.jxc.readapis;

import com.jxc.dbmanager.config.ElasticsearchConfig;
import com.jxc.readapis.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackageClasses  = {
        UserService.class,
        ElasticsearchConfig.class
})
public class ReadapisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadapisApplication.class, args);
    }

}
