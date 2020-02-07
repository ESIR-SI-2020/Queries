package com.jxc.readapis.services;

import fr.esir.jxc.domain.models.analytics.UserAdded;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface UserAnalyticsService {


    UserAdded newUserAdded(UserAdded userAdded);

    UserAdded getUserAddedById(String id);

    List<UserAdded>  getAllUserAdded();

    int numberOfUserAdded();

    UserAdded getBySpecificDate(String date);

}
