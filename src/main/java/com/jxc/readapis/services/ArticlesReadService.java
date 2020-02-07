package com.jxc.readapis.services;

import fr.esir.jxc.domain.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Service
public class ArticlesReadService {

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    public List<Article> getAllArticlesSharedBy(String userId) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(termQuery("sharedBy", userId))
                .build();
        return this.elasticsearchOperations.queryForList(searchQuery,Article.class);

    }


}
