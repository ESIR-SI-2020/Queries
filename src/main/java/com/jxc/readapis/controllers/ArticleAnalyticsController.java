package com.jxc.readapis.controllers;

import com.jxc.readapis.dto.Count;
import com.jxc.readapis.services.ArticleAnalyticsService;
import fr.esir.jxc.domain.models.analytics.ArticleAdded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/analytics/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleAnalyticsController {

    private ArticleAnalyticsService articleAnalyticsService;

    public ArticleAnalyticsController(@Autowired ArticleAnalyticsService service) {
        this.articleAnalyticsService = service;
    }

    @RequestMapping("/")
    public ResponseEntity<List<ArticleAdded>> getAllArticles() {
        return new ResponseEntity<>(articleAnalyticsService.getAllArticleAdded(), HttpStatus.OK);
    }

    @RequestMapping("/{articleId}")
    public ResponseEntity<ArticleAdded> getArticle(@PathVariable String articleId) {
        ArticleAdded articleAdded = articleAnalyticsService.getArticleAddedById(articleId);
        return new ResponseEntity<>(articleAdded, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ArticleAdded> addNewArticles(@RequestBody ArticleAdded articleAdded) {
        articleAnalyticsService.newArticleAdded(articleAdded);
        return new ResponseEntity<>(articleAdded, HttpStatus.CREATED);
    }

    @RequestMapping (value = "/numberOfArticleAdded")
    public ResponseEntity<Count> numberArticleAdded() {
        Count nbOfArticleAdded =  new Count(articleAnalyticsService.numberOfArticleAdded());
        return new ResponseEntity<>(nbOfArticleAdded, HttpStatus.OK);
    }

    @RequestMapping(value = "", params = "date")
    @ResponseBody
    public ResponseEntity<List<ArticleAdded>> getArticleDate(@RequestParam("date") String date) {
        return new ResponseEntity<>(articleAnalyticsService.getBySpecificDate(date), HttpStatus.OK);
    }

    @RequestMapping (value = "/numberOfUserAdded", params = "date")
    @ResponseBody
    public ResponseEntity<Count> numberArticleAddedAtDate(@RequestParam("date") String date) {
        Count nbOfArticleAddedAtDate =  new Count(articleAnalyticsService.getBySpecificDate(date).size());
        return new ResponseEntity<>(nbOfArticleAddedAtDate, HttpStatus.OK);
    }
}
