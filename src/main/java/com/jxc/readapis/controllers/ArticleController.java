package com.jxc.readapis.controllers;

import com.jxc.readapis.dto.ArticleConsultationDTO;
import com.jxc.readapis.exceptions.ArticleNotFoundException;
import com.jxc.readapis.services.ArticleService;
import fr.esir.jxc.domain.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api/v1/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping()
    public ResponseEntity<Page<ArticleConsultationDTO>> getAllArticles(@PageableDefault(size = 20) final Pageable page){
        Page<ArticleConsultationDTO> articlePage = this.articleService.getAllArticles(page);
        if(articlePage.hasContent()){
          return new ResponseEntity<>(articlePage,HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
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
