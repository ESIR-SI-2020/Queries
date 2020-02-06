package com.jxc.readapis.services;

import fr.esir.jxc.domain.models.analytics.ArticleAdded;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface ArticleAnalyticsService {


    ArticleAdded newArticleAdded(ArticleAdded articleAdded);

    ArticleAdded getArticleAddedById(String id);

    List<ArticleAdded>  getAllArticleAdded();

    int numberOfArticleAdded();

    ArticleAdded getBySpecificDate(String date);

    String delete(ArticleAdded articleAdded);

}
