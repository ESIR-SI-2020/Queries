package com.jxc.readapis.controllers;

import com.jxc.readapis.dto.ArticleInfosDTO;
//import com.jxc.readapis.exceptions.UserNotFoundException;
import com.jxc.readapis.services.ArticleService;
import fr.esir.jxc.domain.models.Article;
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
public class ArticleController {

        @Autowired
        private ArticleService articleService;

        @GetMapping()
        public ResponseEntity<List<Article>> getAllArticles() {
            List<Article> articleList = this.articleService.findAllArticles();
            return new ResponseEntity<>(articleList, HttpStatus.OK);
        }
}

