package com.jxc.readapis.controllers;

import com.jxc.readapis.exceptions.UserNotFoundException;
import com.jxc.readapis.dto.UserInfosDTO;
import com.jxc.readapis.services.UserService;

import fr.esir.jxc.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param email The email of the {@link User}
     * @return A {@link ResponseEntity} that contains the {@link UserInfosDTO} if he exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the email of the non existing {@link User}
     */
    @GetMapping("/{email}")
    public ResponseEntity<UserInfosDTO> getUserByEmail(@PathVariable String email) throws UserNotFoundException {
        UserInfosDTO user = this.userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Method to find all the friends of a user
     * @param email The email of the {@link User}
     * @return A {@link ResponseEntity} that contains a list of {@link UserInfosDTO} if the user exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the email of the non existing {@link User}
     */
    @GetMapping("/{email}/friends")
    public ResponseEntity<List<UserInfosDTO>> getUserFriends(@PathVariable String email) throws UserNotFoundException {
        List<UserInfosDTO> friends = this.userService.getUserFriends(email);
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

}
