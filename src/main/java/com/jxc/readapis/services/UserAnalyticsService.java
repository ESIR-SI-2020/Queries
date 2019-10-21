package com.jxc.readapis.services;

import java.util.Date;
import java.util.List;

import com.jxc.dbmanager.models.UserAdded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;


public interface UserAnalyticsService {

    /**
     * Method to find all {@link UserAdded}
     * @return a list that contains all {@link UserAdded}
     */
    List<UserAdded> findAll();

    /**
     * Method to find a {@link UserAdded} by creation date
     * @param date
     * @return a list of {@link UserAdded} created on the date passed in parameters
     */
    List<UserAdded> findAllUserAddedByDate (Date date);

}
