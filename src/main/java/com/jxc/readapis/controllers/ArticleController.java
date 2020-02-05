package com.jxc.readapis.controllers;

import com.jxc.readapis.dto.ArticleInfoDTO;
import com.jxc.readapis.dto.UserInfosDTO;
import com.jxc.readapis.exceptions.UserNotFoundException;
import com.jxc.readapis.services.ArticleService;
import com.jxc.readapis.services.UserService;
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
    ArticleService articleService;

    @GetMapping("/{email}")
    public ResponseEntity<List<ArticleInfoDTO>> getUserArticles(@PathVariable String email) {
        List<ArticleInfoDTO> articles = this.articleService.getUserArticles(email);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/{email}/{tag}")
    public ResponseEntity<List<ArticleInfoDTO>> getUserArticlesByTag(@PathVariable String email, @PathVariable String tag) {
        List<ArticleInfoDTO> articles = this.articleService.getUserArticlesByTag(email, tag);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

}
