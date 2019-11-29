package com.jxc.readapis.services;

import fr.esir.jxc.domain.models.analytics.UserAdded;

import java.util.Date;
import java.util.List;


public interface UserAnalyticsService {

    /**
     * @return a list that contains all {@link UserAdded}
     */
    List<UserAdded> findAllUserAdded();

    /**
     * @param date
     * @return a list of {@link UserAdded} created on the date passed in parameters
     */
    List<UserAdded> findAllUserAddedByDate(Date date);

}
