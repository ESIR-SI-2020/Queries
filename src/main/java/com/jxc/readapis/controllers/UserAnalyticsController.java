package com.jxc.readapis.controllers;

import com.jxc.readapis.dto.Count;
import com.jxc.readapis.services.UserAnalyticsService;
import fr.esir.jxc.domain.models.analytics.UserAdded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/analytics", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserAnalyticsController {

    private UserAnalyticsService userAnalyticsService;

    public UserAnalyticsController(@Autowired UserAnalyticsService service) {
        this.userAnalyticsService = service;
    }

    @GetMapping("/user_added_count")
    public ResponseEntity<Count> getNbOfUsersAdded() {
        List<UserAdded> userAdded = userAnalyticsService.findAllUserAdded();

        return new ResponseEntity<>(new Count(userAdded.size()), HttpStatus.OK);
    }

    /**
     * Return the number of users created today
     * @return
     */
    @GetMapping("/nbofuserscreatedtoday")
    public ResponseEntity<Count> getNbOfUsersCreatedToday() {
        LocalDate localDate = LocalDate.now();

        Date dateDebut = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateFin = Date.from(localDate.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());

        List<UserAdded> userAddedTodayList = userAnalyticsService.findAllUserAddedByDateInterval(dateDebut, dateFin);

        return new ResponseEntity<>(new Count(userAddedTodayList.size()), HttpStatus.OK);
    }


    @PostMapping("/deleteuseradded/{id}")
    public ResponseEntity<HttpStatus> deleteUserAdded(@PathVariable String id) {
        UserAdded userAdded = userAnalyticsService.findOneById(id);

        String documentId = userAnalyticsService.delete(userAdded);
        if(documentId != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
