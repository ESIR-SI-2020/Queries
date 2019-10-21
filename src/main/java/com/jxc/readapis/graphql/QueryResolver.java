package com.jxc.readapis.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jxc.dbmanager.models.User;
import com.jxc.dbmanager.repositories.UserRepository;
import com.jxc.readapis.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    UserRepository userRepository;

    public User userByEmail(String email){
        return userRepository.findById(email).orElseThrow(() -> new UserNotFoundException("User with email : "+ email + " does not exist."));
    }
}
