package com.jxc.app.services;

import com.jxc.app.exceptions.UserNotFoundException;
import com.jxc.app.models.User;
import com.jxc.app.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        List<User> userList = this.userRepository.findAll();
        return userList;
    }

    public User getUserById(String id){
        final Optional<User> optionalUser = this.userRepository.findById(id);
        if (!optionalUser.isPresent()){
            throw new UserNotFoundException("User with id : "+ id + " does not exist.");
        }
        return optionalUser.get();
    }
}
