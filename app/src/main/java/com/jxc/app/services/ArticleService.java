package com.jxc.app.services;

import com.jxc.app.exceptions.*;
import com.jxc.app.models.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import com.jxc.app.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.*;
import java.util.stream.*;

public interface ArticleService {

    /**
     * Method to find user articles by userId
     * @param userId The id of the {@link User}
     * @return A {@link ResponseEntity} that contains the list {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    public List<ArticleConsultationDTO> getUserArticles(String userId);

    /**
     * Method to get all articles of user by userId and articleId
     * @param userId The id of the {@link User}
     * @Param articleId the id of the {@link Article}
     * @return the {@link ArticleConsultationDTO} with articleId if it exists  of the {@link User} if he exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    public ArticleConsultationDTO getUserArticleById(String userId, String articleId);

    /**
     * Method to find user shared articles by userId
     * @param userId The id of the {@link User}
     * @return A {@link ResponseEntity} that contains the list of {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    public List<ArticleConsultationDTO> getUserSharedArticles(String userId);

    /**
     * Method to get all shared articles of user by userId and articleId
     * @param userId The id of the {@link User}
     * @Param articleId the id of the shared {@link Article}
     * @return A {@link ResponseEntity} that contains the list of {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    public ArticleConsultationDTO getUserSharedArticleById(String userId, String articleId);

    /**
     * Method to convert an {@link Article} to an {@link ArticleConsultationDTO}
     * @param article the article to be converted
     * @return an {@link ArticleConsultationDTO}
     */
    public ArticleConsultationDTO articleToArticleConsultationDTO(Article article);
}
