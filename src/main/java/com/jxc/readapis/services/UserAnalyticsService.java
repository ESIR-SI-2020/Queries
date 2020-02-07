package com.jxc.readapis.services;

import fr.esir.jxc.domain.models.analytics.UserAdded;

import java.util.List;


public interface UserAnalyticsService {


    UserAdded newUserAdded(UserAdded userAdded);

    UserAdded getUserAddedById(String id);

    List<UserAdded>  getAllUserAdded();

    int numberOfUserAdded();

    List<UserAdded> getBySpecificDate(String date);

}
