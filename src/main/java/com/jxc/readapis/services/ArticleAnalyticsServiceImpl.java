package com.jxc.readapis.services;

import fr.esir.jxc.domain.models.analytics.ArticleAdded;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

import java.util.*;

@Repository
public class  ArticleAnalyticsServiceImpl implements ArticleAnalyticsService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Override
    public ArticleAdded newArticleAdded(ArticleAdded articleAdded) {
        IndexQuery articleQuery = new IndexQuery();
        articleQuery.setIndexName("pocket");
        articleQuery.setType("article_added");
        articleQuery.setObject(articleAdded);

        LOG.info("article_added indexed: {}", esTemplate.index(articleQuery));
        esTemplate.refresh("pocket");

        return articleAdded;
    }

    @Override
    public ArticleAdded getArticleAddedById(String id) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("pocket").withTypes("article_added")
                .withFilter(QueryBuilders.matchQuery("id", id)).build();
        List<ArticleAdded> articleAdded = esTemplate.queryForList(searchQuery, ArticleAdded.class);
        if(!articleAdded.isEmpty()) {
            return articleAdded.get(0);
        }
        return null;
    }

    @Override
    public List<ArticleAdded> getAllArticleAdded() {
        SearchQuery getAllQuery = new NativeSearchQueryBuilder()
                .withIndices("pocket").withTypes("article_added")
                .withQuery(matchAllQuery()).build();
        return esTemplate.queryForList(getAllQuery, ArticleAdded.class);
    }

    @Override
    public  int numberOfArticleAdded(){
        SearchQuery getAllQuery = new NativeSearchQueryBuilder()
                .withIndices("pocket").withTypes("article_added")
                .withQuery(matchAllQuery()).build();
        List<ArticleAdded> articleAdded =  esTemplate.queryForList(getAllQuery, ArticleAdded.class);

        return articleAdded.size();
    }

    public ArticleAdded getBySpecificDate(String date) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("pocket").withTypes("article_added")
                .withQuery(QueryBuilders.matchQuery("creationDate", date))
                .build();
        List<ArticleAdded> articleAdded = esTemplate.queryForList(searchQuery, ArticleAdded.class);
        if (!articleAdded.isEmpty()) {
            return articleAdded.get(0);
        }
        return null;
    }

}
