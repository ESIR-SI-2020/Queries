package com.jxc.readapis.repositories;

import com.jxc.readapis.utils.ElasticSearchUtils;
import fr.esir.jxc.domain.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Repository
public class EsArticleRepositoryImp implements EsArticleRepository {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public Article save(Article article){
      return article;
    }
    @Override
    public List<Article> findArticlesByOwner(String owner) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(termQuery("owner", owner))
                    .build();
        return this.elasticsearchOperations.queryForList(searchQuery, Article.class);
    }

    @Override
    public Article findArticleById(String articleId) {
        return this.elasticsearchOperations.queryForObject(ElasticSearchUtils.getQuery(articleId), Article.class);
    }

    @Override
    public List<Article> findAll() {
        return this.elasticsearchOperations.queryForList(new NativeSearchQueryBuilder().build(), Article.class);
    }
}
