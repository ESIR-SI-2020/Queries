package com.jxc.readapis.controllers;

import com.jxc.readapis.dto.ArticleConsultationDTO;
import com.jxc.readapis.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping()
    public ResponseEntity<List<ArticleConsultationDTO>> getAllArticles(){
        List<ArticleConsultationDTO> articles = this.articleService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
}
