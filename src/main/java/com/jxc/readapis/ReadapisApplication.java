package com.jxc.readapis;

import com.jxc.readapis.services.UserService;
import fr.esir.jxc.domain.models.Address;
import fr.esir.jxc.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;


@SpringBootApplication
@ComponentScan({
        "fr.esir.jxc.elasticsearch",
        "com.jxc.readapis"
})
public class ReadapisApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ReadapisApplication.class, args);
        }

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        final Address address0 = new Address("postalCode0", "street0", 0, "complement0");
        final Address address1 = new Address("postalCode1", "street1", 1, "complement1");
        final Address address2 = new Address("postalCode2", "street2", 2, "complement2");
        final Address address3 = new Address("postalCode3", "street3", 3, "complement3");

        final User user0 = new User("0", "user0", "Pwd/0", address0);
        final User user1 = new User("1", "user1", "Pwd/1", address1, Arrays.asList(user0.getEmail()));
        final User user2 = new User("2", "user2", "Pwd/2", address2, Arrays.asList(user0.getEmail(), user1.getEmail()));
        final User user3 = new User("3", "user3", "Pwd/3", address3, Arrays.asList(user0.getEmail(), user1.getEmail(), user2.getEmail()));

        userService.save(user0);
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
    }

}
