package com.jxc.readapis.controllers;

import com.jxc.readapis.dto.UserInfosDTO;
import com.jxc.readapis.exceptions.UserNotFoundException;
import com.jxc.readapis.services.UserService;
import fr.esir.jxc.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
// @RequestMapping(value = "${url.version:/api/v1}" + "${url.articles:/users}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
// I'm still looking for how to use the properties to store the url, using ${} is not working for me
public class UserController {

    @Autowired
    ArticleService articleService;

    /**
     * Method to find a user by email
     * @param id The id of the {@link Article}
     * @return A {@link ResponseEntity} that contains the {@link ArticleInfosDTO} if he exist, throw an {@link ArticleNotFoundException} otherwise
     * @throws ArticleNotFoundException An exception with a message that display the id of the non existing {@link Article}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArticleInfosDTO> getArticleById(@PathVariable String id) throws ArticleNotFoundException {
        ArticleInfosDTO article = this.articleService.getArticleById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

}
