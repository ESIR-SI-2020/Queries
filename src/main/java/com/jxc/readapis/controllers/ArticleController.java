package com.jxc.readapis.controllers;

import com.jxc.readapis.dto.ArticleConsultationDTO;
import com.jxc.readapis.grpc.Article;
import com.jxc.readapis.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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

    @GetMapping("/article/{articleId}")
    public ResponseEntity<ArticleConsultationDTO> getArticleById(@PathVariable String articleId){
        Optional<ArticleConsultationDTO> articleDto = articleService.getArticleById(articleId);
        if(articleDto.isPresent()) return new ResponseEntity<>(articleDto.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{owner}")
    public ResponseEntity<List<ArticleConsultationDTO>> getArticlesByOwner(@PathVariable String owner){
        List<ArticleConsultationDTO> articles = this.articleService.getArticlesByOwner(owner);
        if(articles.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<>(articles, HttpStatus.OK);
    }
}
