package com.jxc.readapis.controllers;

import com.jxc.readapis.dto.Count;

import com.jxc.readapis.services.ArticleAnalyticsService;
import fr.esir.jxc.domain.models.analytics.ArticleAdded;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/analytics", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleAnalyticsController {


    private ArticleAnalyticsService articleAnalyticsService;

    public ArticleAnalyticsController(@Autowired ArticleAnalyticsService service) {
        this.articleAnalyticsService = service;
    }

    @GetMapping("/article_added_count")
    public ResponseEntity<Count> getNbOfArticlesAdded() {
        List<ArticleAdded> articleAdded = articleAnalyticsService.findAllArticleAdded();

        return new ResponseEntity<>(new Count(articleAdded.size()), HttpStatus.OK);
    }

    /**
     * Return the number of Articles created
     * @return
     */
    @GetMapping("/nbofarticlescreatedToday")
    public ResponseEntity<Count> getNbOfArticleCreatedToday() {
        LocalDate localDate = LocalDate.now();
        Date today = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<ArticleAdded> articleAddedTodayList = articleAnalyticsService.findAllArticleAddedByDate(today);

        return new ResponseEntity<>(new Count(articleAddedTodayList.size()), HttpStatus.OK);
    }
}