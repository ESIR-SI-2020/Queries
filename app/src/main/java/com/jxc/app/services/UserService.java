package com.jxc.app.services;

import com.jxc.app.models.Article;
import com.jxc.app.models.ArticleConsultationDTO;
import com.jxc.app.models.User;

import java.io.IOException;
import java.util.List;

public interface UserService {

    /**
     * Method to save a {@link User}, only here for testing purpose
     * @param user The {@link User} to save
     * @return The user saved
     */
    User save(User user);

    /**
     * Method to find a {@link User} by id
     * @param id id of the {@link User}
     * @return the {@link User } if he exist, throw an {@link com.jxc.app.exceptions.UserNotFoundException} otherwise
     */
    User findUserById(String id);

    /**
     * Method to find all {@link User}
     * @return a list that contains all {@link User}
     */
    List<User> findAllUsers();

    /**
     * Method to convert an {@link Article} to an {@link ArticleConsultationDTO}
     * @param article the article to be converted
     * @return an {@link ArticleConsultationDTO}
     */
    public ArticleConsultationDTO articleToArticleConsultationDTO(Article article);

}
