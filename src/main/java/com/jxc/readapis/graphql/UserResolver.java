package com.jxc.readapis.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.jxc.dbmanager.models.Address;
import com.jxc.dbmanager.models.Article;
import com.jxc.dbmanager.models.User;
import com.jxc.dbmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResolver implements GraphQLResolver<User> {

    @Autowired
    private UserRepository userRepository;

    public Address address(User user){
        return user.getAddress();
    }

    public List<Article> articles(User user){
        return user.getArticles();
    }

    public List<Article> sharedArticles(User user){
        return user.getSharedArticles();
    }

}
