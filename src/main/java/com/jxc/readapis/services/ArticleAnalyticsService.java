package com.jxc.readapis.services;

import fr.esir.jxc.domain.models.analytics.ArticleAdded;

import java.util.List;



public interface ArticleAnalyticsService {


    ArticleAdded newArticleAdded(ArticleAdded articleAdded);

    ArticleAdded getArticleAddedById(String id);

    List<ArticleAdded>  getAllArticleAdded();

    int numberOfArticleAdded();

    List<ArticleAdded> getBySpecificDate(String date);

}
