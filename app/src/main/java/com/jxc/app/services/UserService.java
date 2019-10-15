package com.jxc.app.services;

import com.jxc.app.models.User;
import com.jxc.app.models.UserInfosDTO;

import java.util.List;

public interface UserService {

    /**
     * Method to save a {@link User}, only here for testing purpose
     * @param user The {@link User} to save
     * @return The user saved
     */
    User save(User user);

    /**
     * Method to find a {@link User} by email
     * @param email the email of the {@link User}
     * @return the {@link User } if he exist, throw an {@link com.jxc.app.exceptions.UserNotFoundException} otherwise
     */
    UserInfosDTO getUserByEmail(String email);

    /**
     * Method to find all {@link User}
     * @return a list that contains all {@link User}
     */
    List<User> findAllUsers();


    /**
     * Method to list all the friends of a {@link User}
     * @return a list of {@link UserInfosDTO}
     */
    List<UserInfosDTO> getUserFriends(String email);

}
