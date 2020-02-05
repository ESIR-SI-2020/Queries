package com.jxc.readapis.services;

import com.jxc.readapis.dto.ArticleInfoDTO;
import com.jxc.readapis.graphql.exceptions.GraphqlServerSideException;
import com.jxc.readapis.mappers.ArticleMapper;
import fr.esir.jxc.domain.models.Article;
import fr.esir.jxc.domain.models.User;
import fr.esir.jxc.elasticsearch.config.ElasticsearchConfig;
import fr.esir.jxc.elasticsearch.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Service
public class ArticleServiceImp implements ArticleService {

    @Autowired
    ElasticsearchConfig elasticsearchConfig;

    @Override
    public List<Article> getUserArticles(String owner){
        List<Article> articles = new ArrayList<>();
        if(!StringUtils.isEmpty(owner)){
            SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchQuery("owner", owner.toLowerCase()))
                    .build();
            articles.addAll(elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, Article.class));
        } else {
            throw new GraphqlServerSideException("Unsupported value. In order to filter by owner, value must be a String");
        }
        return articles;
    }

    @Override
    public List<Article> getUserArticlesByTag(String owner, String tag) {
        List<Article> articles = new ArrayList<>();
        if(!StringUtils.isEmpty(owner)){
            SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(matchQuery("owner", owner.toLowerCase()))
                    .withQuery(matchQuery("tag", tag.toLowerCase()))
                    .build();
            articles.addAll(elasticsearchConfig.elasticsearchTemplate().queryForList(searchQuery, Article.class));
        } else {
            throw new GraphqlServerSideException("Unsupported value. In order to filter by owner, value must be a String");
        }
        return articles;
    }
}
