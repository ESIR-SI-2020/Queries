package com.jxc.readapis.controllers;

import com.jxc.readapis.dto.Count;
import com.jxc.readapis.services.ArticleAnalyticsService;
import fr.esir.jxc.domain.models.analytics.ArticleAdded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/analytics/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleAnalyticsController {

    private ArticleAnalyticsService articleAnalyticsService;

    public ArticleAnalyticsController(@Autowired ArticleAnalyticsService service) {
        this.articleAnalyticsService = service;
    }

    @GetMapping("/all")
    public List<ArticleAdded> getAllArticles() {
        return articleAnalyticsService.getAllArticleAdded();
    }

    @RequestMapping("/id/{articleId}")
    public ArticleAdded getArticle(@PathVariable String articleId) {
        ArticleAdded articleAdded = articleAnalyticsService.getArticleAddedById(articleId);
        return articleAdded;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ArticleAdded addNewArticles(@RequestBody ArticleAdded articleAdded) {
        articleAnalyticsService.newArticleAdded(articleAdded);
        return articleAdded;
    }

    @GetMapping (value = "/numberArticleAdded")
    public int numberArticleAdded() {
        return articleAnalyticsService.numberOfArticleAdded();
    }

    @RequestMapping("/date/{date}")
    public ArticleAdded getArticleDate(@PathVariable String date) {
        return (ArticleAdded) articleAnalyticsService.getBySpecificDate(date);
    }

    @PostMapping("/deleteArticleAdded/{id}")
    public ResponseEntity<HttpStatus> deleteArticleAdded(@PathVariable String id) {
        ArticleAdded articleAdded = articleAnalyticsService.getArticleAddedById(id);

        String documentId = articleAnalyticsService.delete(articleAdded);
        if(documentId != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
