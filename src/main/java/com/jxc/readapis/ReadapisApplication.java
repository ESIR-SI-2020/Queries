package com.jxc.readapis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({
        "fr.esir.jxc.elasticsearch",
        "com.jxc.readapis"
})
public class ReadapisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadapisApplication.class, args);
    }

}
