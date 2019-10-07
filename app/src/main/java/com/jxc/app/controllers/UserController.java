package com.jxc.app.controllers;

import com.jxc.app.models.User;
import com.jxc.app.models.User;
import com.jxc.app.services.UserService;
import com.jxc.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        return new ResponseEntity<>(this.userService.getUserById(id), HttpStatus.OK);
    }
}
