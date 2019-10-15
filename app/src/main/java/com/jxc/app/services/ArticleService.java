package com.jxc.app.services;


import com.jxc.app.exceptions.UserNotFoundException;
import com.jxc.app.models.Article;
import com.jxc.app.models.dto.ArticleConsultationDTO;
import com.jxc.app.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArticleService {

    /**
     * Method to find user articles by user email
     * @param email The id of the {@link User}
     * @return A {@link ResponseEntity} that contains the list {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    public List<ArticleConsultationDTO> getUserArticles(String email);

    /**
     * Method to get all articles of user by user email and articleId
     * @param email The id of the {@link User}
     * @Param articleId the id of the {@link Article}
     * @return the {@link ArticleConsultationDTO} with articleId if it exists  of the {@link User} if he exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    public ArticleConsultationDTO getUserArticleById(String email, String articleId);

    /**
     * Method to find user shared articles by user email
     * @param email The id of the {@link User}
     * @return A {@link ResponseEntity} that contains the list of {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    public List<ArticleConsultationDTO> getUserSharedArticles(String email);

    /**
     * Method to get all shared articles of user by user email and articleId
     * @param email The id of the {@link User}
     * @Param articleId the id of the shared {@link Article}
     * @return A {@link ResponseEntity} that contains the list of {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    public ArticleConsultationDTO getUserSharedArticleById(String email, String articleId);

}
