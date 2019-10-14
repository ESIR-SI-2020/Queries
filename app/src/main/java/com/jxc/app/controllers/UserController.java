package com.jxc.app.controllers;

import com.jxc.app.exceptions.UserNotFoundException;
import com.jxc.app.models.User;
import com.jxc.app.models.UserInfosDTO;
import com.jxc.app.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
// @RequestMapping(value = "${url.version:/api/v1}" + "${url.users:/users}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
// I'm still looking for how to use the properties to store the url, using ${} is not working for me
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = this.userService.findAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    /**
     * Method to find a user by email
     * @param userId The email of the {@link User}
     * @return A {@link ResponseEntity} that contains the {@link UserInfosDTO} if he exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the email of the non existing {@link User}
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserInfosDTO> getUserByEmail(@PathVariable String userId) throws UserNotFoundException {
            User user = this.userService.findUserByEmail(userId);
            UserInfosDTO userInfo = this.userService.UserToUserInfosDTO(user);
            return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    /**
     * Method to find all the friends of a user
     * @param userId The email of the {@link User}
     * @return A {@link ResponseEntity} that contains a list of {@link UserInfosDTO} if the user exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the email of the non existing {@link User}
     */
    @GetMapping("/{userId}/friends")
    public ResponseEntity<List<UserInfosDTO>> getUserFriends(@PathVariable String userId) throws UserNotFoundException {
        User user = this.userService.findUserByEmail(userId);
        List<UserInfosDTO> friends = this.userService.ListFriends(user);
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

}
