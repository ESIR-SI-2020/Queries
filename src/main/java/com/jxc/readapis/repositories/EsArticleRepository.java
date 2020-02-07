package com.jxc.readapis.repositories;

import fr.esir.jxc.domain.models.Article;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EsArticleRepository {

    Article save(Article article);
    List<Article> findAll();
    Article findArticleById(String articleId);
    List<Article> findArticlesByOwner(String articleId);
}
