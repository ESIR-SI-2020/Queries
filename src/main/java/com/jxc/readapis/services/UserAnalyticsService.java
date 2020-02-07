package com.jxc.readapis.services;

import fr.esir.jxc.domain.models.analytics.UserAdded;

import java.util.Date;
import java.util.List;


public interface UserAnalyticsService {

    /**
     * Method to save a {@link UserAdded} (for testing purpose)
     * @param userAdded
     * @return
     */
    UserAdded save(UserAdded userAdded);

    /**
     * Method to delete {@link UserAdded}
     * @param userAdded
     * @return documentId of the document deleted
     */
    String delete(UserAdded userAdded);

    /**
     * @param id
     * @return the userAdded corresponding to the specified id
     */
    UserAdded findOneById(String id);

    /**
     * @return a list that contains all {@link UserAdded}
     */
    List<UserAdded> findAllUserAdded();

    /**
     * @param dateDebut
     * @param dateFin
     * @return a list of {@link UserAdded} created on the date passed in parameters
     */
    List<UserAdded> findAllUserAddedByDateInterval(Date dateDebut, Date dateFin);

}
