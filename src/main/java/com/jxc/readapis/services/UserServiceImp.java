package com.jxc.readapis.services;

import fr.esir.jxc.domain.models.User;
import fr.esir.jxc.elasticsearch.repositories.UserRepository;
import com.jxc.readapis.dto.UserInfosDTO;
import com.jxc.readapis.exceptions.UserNotFoundException;
import com.jxc.readapis.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public UserInfosDTO getUserByEmail(String email){
        User user = (this.userRepository.findById(email))
                .orElseThrow(() -> new UserNotFoundException("User with email : "+ email + " does not exist."));
        return UserMapper.convertToUserInfosDTO(user);
    }

    public Page<UserInfosDTO> findAllUsers(Pageable page){
        List<UserInfosDTO> userInfosDTOS = new ArrayList<>();
        Page<User> users = userRepository.findAll(page);
        users.getContent().forEach(user -> userInfosDTOS.add(UserMapper.convertToUserInfosDTO(user)));
        return new PageImpl<>(userInfosDTOS, page, users.getTotalElements());
    }

    public List<UserInfosDTO> getUserFriends(String email) {
        User user = (this.userRepository.findById(email))
                .orElseThrow(() -> new UserNotFoundException("User with email : "+ email + " does not exist."));
        List<String> friendsEmails = user.getFriends() != null ? user.getFriends() : Collections.emptyList();
        List<UserInfosDTO> friends = new ArrayList<>();
        friendsEmails.forEach(friendEmail -> {
            Optional<User> friend = userRepository.findById(email);
            friend.ifPresent(value -> friends.add(UserMapper.convertToUserInfosDTO(value)));
        });
        return friends;
    }

}
