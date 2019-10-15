package com.jxc.app.controllers;


import com.jxc.app.exceptions.ArticleNotFoundException;
import com.jxc.app.exceptions.UserNotFoundException;
import com.jxc.app.models.Article;
import com.jxc.app.models.ArticleConsultationDTO;
import com.jxc.app.models.User;
import com.jxc.app.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * Method to find user articles by userId
     * @param userId The id of the {@link User}
     * @return A {@link ResponseEntity} that contains the list {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    @GetMapping("/{userId}/articles")
    public ResponseEntity<List<ArticleConsultationDTO>> getUserArticles(@PathVariable String userId) throws UserNotFoundException, IOException {
        List<ArticleConsultationDTO> articles = this.articleService.getUserArticles(userId);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    /**
     * Method to get all articles of user by userId and articleId
     * @param userId The id of the {@link User}
     * @Param articleId the id of the {@link Article}
     * @return A {@link ResponseEntity} that contains the list {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    @GetMapping("/{userId}/articles/{articleId}")
    public ResponseEntity<ArticleConsultationDTO> getUserArticleById(@PathVariable(name="userID") String userId, @PathVariable(name="articleId") String articleId) throws UserNotFoundException, ArticleNotFoundException {
        ArticleConsultationDTO article = this.articleService.getUserArticleById(userId, articleId);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    /**
     * Method to find user shared articles by userId
     * @param userId The id of the {@link User}
     * @return A {@link ResponseEntity} that contains the list of {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    @GetMapping("/{userId}/articles/shared")
    public ResponseEntity<List<ArticleConsultationDTO>> getUserSharedArticles(@PathVariable String userId) throws UserNotFoundException, IOException {
        List<ArticleConsultationDTO> sharedArticles = this.articleService.getUserSharedArticles(userId);
        return new ResponseEntity<>(sharedArticles, HttpStatus.OK);
    }

    /**
     * Method to get all shared articles of user by userId and articleId
     * @param userId The id of the {@link User}
     * @Param articleId the id of the shared {@link Article}
     * @return A {@link ResponseEntity} that contains the list of {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    @GetMapping("/{userId}/articles/shared/{articleId}")
    public ResponseEntity<ArticleConsultationDTO> getUserSharedArticleById(@PathVariable(name="userID") String userId, @PathVariable(name="articleId") String articleId) throws UserNotFoundException, ArticleNotFoundException {
        ArticleConsultationDTO sharedArticle = this.articleService.getUserSharedArticleById(userId, articleId);
        return new ResponseEntity<>(sharedArticle, HttpStatus.OK);
    }
}
