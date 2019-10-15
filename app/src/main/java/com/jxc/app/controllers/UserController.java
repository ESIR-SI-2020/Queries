package com.jxc.app.controllers;

import com.jxc.app.exceptions.ArticleNotFoundException;
import com.jxc.app.exceptions.UserNotFoundException;
import com.jxc.app.models.User;
import com.jxc.app.models.Article;
import com.jxc.app.models.ArticleConsultationDTO;
import com.jxc.app.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
// @RequestMapping(value = "${url.version:/api/v1}" + "${url.users:/users}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
// I'm still looking for how to use the properties to store the url, using ${} is not working for me
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = this.userService.findAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    /**
     * Method to find a user by id
     * @param id The id of the {@link User}
     * @return A {@link ResponseEntity} that contains the {@link User} if he exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) throws UserNotFoundException {
            User user = this.userService.findUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Method to find user articles by userId
     * @param userId The id of the {@link User}
     * @return A {@link ResponseEntity} that contains the list {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link UserNotFoundException} otherwise
     * @throws UserNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    @GetMapping("/{userId}/articles")
    public ResponseEntity<List<ArticleConsultationDTO>> getUserArticles(@PathVariable String userId) throws UserNotFoundException, IOException {
        List<ArticleConsultationDTO> articles = this.userService.findUserById(userId).getArticles().stream()
                .map(userService::articleToArticleConsultationDTO)
                .collect(Collectors.toList());
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
        ArticleConsultationDTO article = this.userService.findUserById(userId).getArticles().stream()
                .map(userService::articleToArticleConsultationDTO)
                .filter(articleElement -> articleId.equals(articleElement.getId())).findFirst()
                .orElseThrow(() ->
                {
                    return new ArticleNotFoundException(String.format("Article with id : "+ articleId + " does not exist.", articleId));
                });
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
        List<ArticleConsultationDTO> sharedArticles = this.userService.findUserById(userId).getSharedArticles().stream()
                .map(userService::articleToArticleConsultationDTO)
                .collect(Collectors.toList());
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
        ArticleConsultationDTO sharedArticle = this.userService.findUserById(userId).getSharedArticles().stream()
                .map(userService::articleToArticleConsultationDTO)
                .filter(articleElement -> articleId.equals(articleElement.getId())).findFirst()
                .orElseThrow(() ->
                {
                    return new ArticleNotFoundException(String.format("Article with id : "+ articleId + " does not exist.", articleId));
                });
        return new ResponseEntity<>(sharedArticle, HttpStatus.OK);
    }


}
