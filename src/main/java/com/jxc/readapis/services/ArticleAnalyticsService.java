package com.jxc.readapis.services;

import fr.esir.jxc.domain.models.Article;
import fr.esir.jxc.domain.models.analytics.ArticleAdded;

import java.util.Date;
import java.util.List;

public interface ArticleAnalyticsService {

    /**
     * @return a list that contains all {@link ArticleAdded}
     */
    List<ArticleAdded> findAllArticleAdded();

    /**
     * @param date
     * @return a list of {@link ArticleAdded} created on the date passed in parameters
     */
    List<ArticleAdded> findAllArticleAddedByDate(Date date);

}
