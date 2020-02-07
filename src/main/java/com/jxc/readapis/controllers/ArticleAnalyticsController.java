package com.jxc.readapis.controllers;

import com.jxc.readapis.dto.Count;
import com.jxc.readapis.services.ArticleAnalyticsService;
import fr.esir.jxc.domain.models.analytics.ArticleAdded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/articlesAnalytics", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    public ResponseEntity getArticleDate(@RequestParam("date") String date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>("{\"Error\": \"Invalid parameter date\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(articleAnalyticsService.getBySpecificDate(date), HttpStatus.OK);
    }

    @RequestMapping (value = "/numberOfUserAdded", params = "date")
    @ResponseBody
    public ResponseEntity numberArticleAddedAtDate(@RequestParam("date") String date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>("{\"Error\": \"Invalid parameter date\"}",HttpStatus.BAD_REQUEST);
        }
        Count nbOfArticleAddedAtDate =  new Count(articleAnalyticsService.getBySpecificDate(date).size());
        return new ResponseEntity<>(nbOfArticleAddedAtDate, HttpStatus.OK);
    }
}
