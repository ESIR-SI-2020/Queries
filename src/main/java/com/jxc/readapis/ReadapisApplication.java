package com.jxc.readapis;

import fr.esir.jxc.elasticsearch.config.ElasticsearchConfig;
import com.jxc.readapis.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "fr.esir.jxc", basePackageClasses  = {
        UserService.class,
        ElasticsearchConfig.class
})
public class ReadapisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadapisApplication.class, args);
    }

}
