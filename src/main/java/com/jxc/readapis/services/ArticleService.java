package com.jxc.readapis.services;

import com.jxc.readapis.dto.ArticleConsultationDTO;
import fr.esir.jxc.domain.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    /**
    * Method to save a {@link Article}
    * @param article The {@link Article} to save
    * @return The article saved
    */
    ArticleConsultationDTO save(Article article);
    /**
     * Method to find an Article by its url
     * @param id
     * @return the article {@link Article} if it exits, throw an ArticleNtFoundException otherwise
     */
    Optional<ArticleConsultationDTO> getArticleById(String id);

    /**
     * Method to find all the articles of a user
     * @param owner of the articles
     * @return the name of the owner of the article if the article exists, throw an ArticleNtFoundException otherwise
     */
    List<ArticleConsultationDTO> getArticlesByOwner(String owner);

    /**
     * Find all articles
     * @return a list of all articles
     */
    List<ArticleConsultationDTO> getAllArticles();

}
