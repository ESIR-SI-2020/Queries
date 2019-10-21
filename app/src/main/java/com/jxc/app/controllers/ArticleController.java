package com.jxc.app.controllers;

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

import com.jxc.app.exceptions.RessourceNotFoundException;
import com.jxc.app.models.Article;
import com.jxc.app.models.dto.ArticleConsultationDTO;
import com.jxc.app.models.User;
import com.jxc.app.services.ArticleService;

@RestController
@RequestMapping(value = "/api/v1/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * Method to find user articles by user email
     * @param email The id of the {@link User}
     * @return A {@link ResponseEntity} that contains the list {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link RessourceNotFoundException} otherwise
     * @throws RessourceNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    @GetMapping("?owner={email}")
    public ResponseEntity<List<ArticleConsultationDTO>> getUserArticles(@PathVariable String email) throws RessourceNotFoundException, IOException {
        List<ArticleConsultationDTO> articles = this.articleService.getUserArticles(email);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    /**
     * Method to get all articles of user by email and articleId
     * @param email The id of the {@link User}
     * @Param articleId the id of the {@link Article}
     * @return A {@link ResponseEntity} that contains the list {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link RessourceNotFoundException} otherwise
     * @throws RessourceNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    @GetMapping("/{articleId}/?owner={email}") //email de l'utilisateur pourra par la suite être recupéré depuis le contexte d'authentification
    public ResponseEntity<ArticleConsultationDTO> getUserArticleById(@PathVariable(name="email") String email, @PathVariable(name="articleId") String articleId) throws RessourceNotFoundException {
        ArticleConsultationDTO article = this.articleService.getUserArticleById(email, articleId);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    /**
     * Method to find user shared articles by user email
     * @param email The id of the {@link User}
     * @return A {@link ResponseEntity} that contains the list of {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link RessourceNotFoundException} otherwise
     * @throws RessourceNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    @GetMapping("/shared/?owner={email}")
    public ResponseEntity<List<ArticleConsultationDTO>> getUserSharedArticles(@PathVariable String email) throws RessourceNotFoundException, IOException {
        List<ArticleConsultationDTO> sharedArticles = this.articleService.getUserSharedArticles(email);
        return new ResponseEntity<>(sharedArticles, HttpStatus.OK);
    }

    /**
     * Method to get all shared articles of user by user email and articleId
     * @param email The id of the {@link User}
     * @Param articleId the id of the shared {@link Article}
     * @return A {@link ResponseEntity} that contains the list of {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link RessourceNotFoundException} otherwise
     * @throws RessourceNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    @GetMapping("/shared/{articleId}?owner={email}")
    public ResponseEntity<ArticleConsultationDTO> getUserSharedArticleById(@PathVariable(name="email") String email, @PathVariable(name="articleId") String articleId) throws RessourceNotFoundException {
        ArticleConsultationDTO sharedArticle = this.articleService.getUserSharedArticleById(email, articleId);
        return new ResponseEntity<>(sharedArticle, HttpStatus.OK);
    }
}
