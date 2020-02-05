package com.jxc.readapis.services;

import fr.esir.jxc.domain.models.analytics.ArticleAdded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleAnalyticsServiceImpl implements ArticleAnalyticsService{

    @Autowired
    private ElasticsearchOperations elasticsearchTemplate;

    @Override
    public List<ArticleAdded> findAllArticleAdded() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().build();
        return this.elasticsearchTemplate.queryForList(searchQuery, ArticleAdded.class);
    }

    @Override
    public List<ArticleAdded> findAllArticleAddedByDate (Date date) {

        return null;
    }
}