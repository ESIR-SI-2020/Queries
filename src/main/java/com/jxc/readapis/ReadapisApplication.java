package com.jxc.readapis;

import com.jxc.readapis.grpc.services.ArticleServiceImpl;
import com.jxc.readapis.grpc.services.UserServiceImpl;
import fr.esir.jxc.domain.models.Address;
import fr.esir.jxc.domain.models.Article;
import fr.esir.jxc.domain.models.User;
import fr.esir.jxc.elasticsearch.repositories.ArticleRepository;
import fr.esir.jxc.elasticsearch.repositories.UserRepository;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


@SpringBootApplication
@ComponentScan({
        "fr.esir.jxc.elasticsearch",
        "com.jxc.readapis"
})
public class ReadapisApplication implements CommandLineRunner {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(ReadapisApplication.class, args);
        Server server = ServerBuilder
                .forPort(8083)
                .addService(new UserServiceImpl())
                .addService(new ArticleServiceImpl())
                .build();

        server.start();
        server.awaitTermination();
        }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void run(String... args) throws Exception {

        final Address address0 = new Address("postalCode0", "street0", 0, "complement0");
        final Address address1 = new Address("postalCode1", "street1", 1, "complement1");
        final Address address2 = new Address("postalCode2", "street2", 2, "complement2");
        final Address address3 = new Address("postalCode3", "street3", 3, "complement3");

        final User user0 = new User("user0", "username0", "Pwd/0", address0);
        final User user1 = new User("user1", "username1", "Pwd/1", address1, Collections.singletonList(user0.getEmail()));
        final User user2 = new User("user2", "username2", "Pwd/2", address2, Arrays.asList(user0.getEmail(), user1.getEmail()));
        final User user3 = new User("user3", "username3", "Pwd/3", address3, Arrays.asList(user0.getEmail(), user1.getEmail(), user2.getEmail()));

        userRepository.save(user0);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        final Article article0 = new Article("article0", "url0", "user0", "", new ArrayList<>(), new ArrayList<>());
        final Article article1 = new Article("article1", "url1", "user0", "", Arrays.asList("tag1", "tag2"), new ArrayList<>());
        final Article article2 = new Article("article2", "url2", "user1", "user0", Arrays.asList("tag2", "tag2bis"), Arrays.asList("suggestedTag2", "suggestedTag2bis"));
        final Article article3 = new Article("article3", "url3", "user2", "", Collections.singletonList("tag3"), new ArrayList<>());

        articleRepository.save(article0);
        articleRepository.save(article1);
        articleRepository.save(article2);
        articleRepository.save(article3);

    }

}
