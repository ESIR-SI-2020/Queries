package com.jxc.readapis.services;

import com.jxc.readapis.dto.ArticleConsultationDTO;


import java.util.List;

public interface ArticleService {

    /**
     * Method to find an Article by its url
     * @param id
     * @return the article {@link Article} if it exits, throw an ArticleNtFoundException otherwise
     */
    ArticleConsultationDTO getArticleById(String id);

    /**
     * Method to find the owner of an article
     * @param id the url of the article
     * @return the name of the owner of the article if the article exsts, throw an ArticleNtFoundException otherwise
     */
    String getArticleOwner(String id);

    /**
     * Find all articles
     * @return a list of all articles
     */
    List<ArticleConsultationDTO> getAllArticles();

}
