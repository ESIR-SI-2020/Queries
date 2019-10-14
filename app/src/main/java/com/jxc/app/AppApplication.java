package com.jxc.app;

import com.jxc.app.models.Address;
import com.jxc.app.models.User;
import com.jxc.app.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@SpringBootApplication
@Slf4j
public class AppApplication implements CommandLineRunner {

    @Autowired
    private ElasticsearchOperations esOperation;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        printElasticSearchInfos();

        Address address0 = new Address("postalCode0", "street0", 0, "complement0");
        Address address1 = new Address("postalCode1", "street1", 1, "complement1");
        Address address2 = new Address("postalCode2", "street2", 2, "complement2");

        User user0 = new User("0", "user0", "Pwd/1", address0);
        User user1 = new User("1", "user1", "Pwd/2", address1, Arrays.asList(user0.getEmail()) , Arrays.asList(), Arrays.asList());
        User user2 = new User("2", "user2", "Pwd/3", address2, Arrays.asList(user0.getEmail(), user1.getEmail()) , Arrays.asList(), Arrays.asList());

        userService.save(user0);

        User user = userService.findUserByEmail("0");
        log.info("The user :" + user + "has been saved in database");

        userService.save(user1);
        userService.save(user2);

        List<User> userList = userService.findAllUsers();
        log.info("All users in db :");
        userList.forEach(x -> log.info(x.toString()));
    }

    /**
     * A method that display information about the ElasticSearch database
     */
    private void printElasticSearchInfos() {

        log.info("--- ElasticSearchInfos : Start ---");
        Client client = esOperation.getClient();
        Map<String, String> asMap = client.settings().getAsMap();

        asMap.forEach((k, v) -> {
            log.info(k + " = " + v);
        });
        log.info("--- ElasticSearchInfos : End ---");
    }

}
