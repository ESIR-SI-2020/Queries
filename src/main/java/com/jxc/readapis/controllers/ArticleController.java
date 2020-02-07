package com.jxc.readapis.controllers;


import com.jxc.readapis.dto.ArticleInfosDTO;
import com.jxc.readapis.exceptions.ArticleNotFoundException;
import com.jxc.readapis.services.ArticleService;
import com.jxc.readapis.services.ArticleServiceImpl;
import fr.esir.jxc.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(@Autowired ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * Method to find all shared articles of a specific user
     * @param userId The email of the {@link User}
     * @return A {@link ResponseEntity} that contains a list of {@link ArticleInfosDTO} if the user exist, throw an {@link ArticleNotFoundException} otherwise
     *      * @throws ArticleNotFoundException An exception with a message that display the email of the non existing {@link User}
     */
    @GetMapping("/shared")
    public ResponseEntity<List<ArticleInfosDTO>> getAllSharedArticle(@RequestParam(name="userId") String userId) {
        List<ArticleInfosDTO> articles = this.articleService.getAllSharedArticles(userId);
        System.out.println("articles: "+ articles);
        if(!articles.isEmpty()){
            return new ResponseEntity<>(articles, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
