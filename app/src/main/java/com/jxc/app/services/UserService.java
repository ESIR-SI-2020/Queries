package com.jxc.app.services;

import com.jxc.app.models.User;
import com.jxc.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User getUserById(long id){
        Optional<User> optionalUser = this.userRepository.findById(id);
        return optionalUser.orElse(null);
    }
}
