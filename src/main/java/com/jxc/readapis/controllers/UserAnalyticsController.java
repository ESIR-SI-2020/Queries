package com.jxc.readapis.controllers;

import com.jxc.readapis.dto.Count;
import com.jxc.readapis.services.UserAnalyticsService;
import fr.esir.jxc.domain.models.analytics.UserAdded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/analytics/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserAnalyticsController {

    private UserAnalyticsService userAnalyticsService;

    public UserAnalyticsController(@Autowired UserAnalyticsService service) {
        this.userAnalyticsService = service;
    }

    @GetMapping("/all")
    public List<UserAdded> getAllUsers() {
        return userAnalyticsService.getAllUserAdded();
    }

    @RequestMapping("/id/{userId}")
    public UserAdded getUser(@PathVariable String userId) {
        UserAdded userAdded = userAnalyticsService.getUserAddedById(userId);
        return userAdded;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public UserAdded addNewUsers(@RequestBody UserAdded userAdded) {
        userAnalyticsService.newUserAdded(userAdded);
        return userAdded;
    }

    @GetMapping (value = "/numberUserAdded")
    public int numberUserAdded() {
        return userAnalyticsService.numberOfUserAdded();
    }

    @RequestMapping("/date/{date}")
    public List<UserAdded> getUserDate(@PathVariable String date) {
       return userAnalyticsService.getBySpecificDate(date);
    }

}
