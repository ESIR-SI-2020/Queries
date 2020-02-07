package com.jxc.readapis.controllers;

import com.jxc.readapis.dto.Count;
import com.jxc.readapis.services.UserAnalyticsService;
import fr.esir.jxc.domain.models.analytics.UserAdded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/usersAnalytics", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserAnalyticsController {

    private UserAnalyticsService userAnalyticsService;

    public UserAnalyticsController(@Autowired UserAnalyticsService service) {
        this.userAnalyticsService = service;
    }

    @RequestMapping(value = "/")
    public ResponseEntity<List<UserAdded>> getAllUsers() {
        return new ResponseEntity<>(userAnalyticsService.getAllUserAdded(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}")
    public  ResponseEntity<UserAdded> getUser(@PathVariable String userId) {
        UserAdded userAdded = userAnalyticsService.getUserAddedById(userId);
        return new ResponseEntity<>(userAdded, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<UserAdded> addNewUsers(@RequestBody UserAdded userAdded) {
        userAnalyticsService.newUserAdded(userAdded);
        return new ResponseEntity<>(userAdded, HttpStatus.CREATED);
    }

    @RequestMapping (value = "/numberOfUserAdded")
    public ResponseEntity<Count> numberUserAdded() {
        Count nbOfUserAdded =  new Count(userAnalyticsService.numberOfUserAdded());
        return new ResponseEntity<>(nbOfUserAdded, HttpStatus.OK);
    }

    @RequestMapping(value = "", params = "date")
    @ResponseBody
    public ResponseEntity getUserDate(@RequestParam("date") String date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>("{\"Error\": \"Invalid parameter date\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userAnalyticsService.getBySpecificDate(date), HttpStatus.OK);
    }

    @RequestMapping (value = "/numberOfUserAdded", params = "date")
    @ResponseBody
    public ResponseEntity numberUserAddedAtDate(@RequestParam("date") String date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>("{\"Error\": \"Invalid parameter date\"}",HttpStatus.BAD_REQUEST);
        }
        Count nbOfUserAddedAtDate =  new Count(userAnalyticsService.getBySpecificDate(date).size());
        return new ResponseEntity<>(nbOfUserAddedAtDate, HttpStatus.OK);
    }

}
