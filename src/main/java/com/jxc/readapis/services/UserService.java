package com.jxc.readapis.services;
import com.jxc.readapis.dto.UserInfosDTO;
import fr.esir.jxc.domain.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @return the {@link User } if he exist, throw an {@link com.jxc.readapis.exceptions.UserNotFoundException} otherwise
     */
    UserInfosDTO getUserByEmail(String email);

    /**
     * Method to find all {@link User}
     * @return a list that contains all {@link User}
     */
    Page<UserInfosDTO> findAllUsers(Pageable page);

    /**
     * Method to list all the friends of a {@link User}
     * @return a list of {@link UserInfosDTO}
     */
    List<UserInfosDTO> getUserFriends(String email);

}
