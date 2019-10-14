package com.jxc.app.services;

import com.jxc.app.exceptions.UserNotFoundException;
import com.jxc.app.models.User;
import com.jxc.app.models.UserInfosDTO;
import com.jxc.app.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public User findUserByEmail(String email){
        return this.userRepository.findById(email)
                .orElseThrow(() -> new UserNotFoundException("User with email : "+ email + " does not exist."));
    }

    public List<User> findAllUsers(){
        return this.userRepository.findAll().getContent();
    }

    public UserInfosDTO UserToUserInfosDTO(User user){
        return new UserInfosDTO(user.getEmail(), user.getUsername(), user.getAddress());
    }

    public List<UserInfosDTO> ListFriends(User user) {
        List<String> friendsEmails = user.getFriends();
        List<UserInfosDTO> friends = new ArrayList<>();
        for (String email : friendsEmails) {
            Optional<User> userTmp = this.userRepository.findById(email);
            if (userTmp.isPresent()){
                friends.add(this.UserToUserInfosDTO(userTmp.get()));
            }
        }
        return friends;
    }

}
