package com.jxc.readapis;

import com.jxc.readapis.services.UserAnalyticsService;
import com.jxc.readapis.services.UserAnalyticsServiceImpl;
import fr.esir.jxc.domain.models.analytics.UserAdded;
import fr.esir.jxc.elasticsearch.config.ElasticsearchConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;


@SpringBootApplication
@ComponentScan(basePackages = "fr.esir.jxc, com.jxc.readapis", basePackageClasses  = {
        ElasticsearchConfig.class,
        UserAnalyticsService.class
})
public class ReadapisApplication {


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ReadapisApplication.class, args);
        UserAnalyticsService service= context.getBean(UserAnalyticsServiceImpl.class);
        List<UserAdded> result = service.findAllUserAdded();
        System.out.println(result);


    }

}
