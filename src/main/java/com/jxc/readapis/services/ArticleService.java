package com.jxc.readapis.services;

import com.jxc.readapis.dto.ArticleInfosDTO;
import fr.esir.jxc.domain.models.Article;

import java.util.List;



public interface ArticleService {

    /**
     * Method to save a {@link Article}, only here for testing purpose
     * @return The article saved
     */
    Article save(Article article);

    /**
     * Method to find all {@link Article}
     * @return a list that contains all {@link Article}
     */
    List<Article> findAllArticles();
}
