package com.jxc.app.services;

import com.jxc.app.exceptions.UserNotFoundException;
import com.jxc.app.mapper.UserMapper;
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

    public UserInfosDTO getUserByEmail(String email){
        User user = (this.userRepository.findById(email))
                .orElseThrow(() -> new UserNotFoundException("User with email : "+ email + " does not exist."));
        return UserMapper.ConvertToUserInfosDTO(user);
    }

    public List<User> findAllUsers(){
        return this.userRepository.findAll().getContent();
    }

    public List<UserInfosDTO> getUserFriends(String email) {
        User user = (this.userRepository.findById(email))
                .orElseThrow(() -> new UserNotFoundException("User with email : "+ email + " does not exist."));
        List<String> friendsEmails = user.getFriends();
        List<UserInfosDTO> friends = new ArrayList<>();
        List<String> emailsToDelete = new ArrayList<>();
        for (String emailTmp : friendsEmails) {
            Optional<User> userTmp = this.userRepository.findById(emailTmp);
            if (userTmp.isPresent()){
                friends.add(UserMapper.ConvertToUserInfosDTO(userTmp.get()));
            }else {
                emailsToDelete.add(emailTmp);
            }
        }
        if (!emailsToDelete.isEmpty()){
            for (String emailTmp : emailsToDelete) {
                friendsEmails.remove(emailTmp);
            }
            user.setFriends(friendsEmails);
            this.userRepository.save(user);
        }
        return friends;
    }

}
