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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api/v1/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public ResponseEntity<List<ArticleConsultationDTO>> getAllArticles(@PageableDefault(size = 20) final Pageable page){
        Optional<List<ArticleConsultationDTO>> articlePage = Optional.of(this.articleService.getAllArticles());
        if(articlePage.isPresent()){
          return new ResponseEntity<>(articlePage.get(),HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/article/{articleId}")
    public ResponseEntity<ArticleConsultationDTO> getArticleById(@PathVariable String articleId){
        Optional<ArticleConsultationDTO> articleDto = articleService.getArticleById(articleId);
        if(articleDto.isPresent()) return new ResponseEntity<>(articleDto.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{owner}")
    public ResponseEntity<List<ArticleConsultationDTO>> getArticlesByOwner(@PathVariable String owner){
        List<ArticleConsultationDTO> articles = this.articleService.getArticlesByOwner(owner);
        if(articles.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<ArticleConsultationDTO> postArticle(Article article){
      Optional<ArticleConsultationDTO> optPresent = this.articleService.getArticleById(article.getId());
      if(!optPresent.isPresent()){
        Optional<ArticleConsultationDTO> optArticle = Optional.of(this.articleService.save(article));
        return new ResponseEntity<>(optArticle.get(),HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
      }

    }
}
