package com.jxc.readapis.services;

import com.jxc.readapis.dto.ArticleConsultationDTO;
import fr.esir.jxc.domain.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    /**
    * Method to save a {@link User}, only here for testing purpose
    * @param user The {@link User} to save
    * @return The user saved
    */
    User save(User user);
    /**
     * Method to find an Article by its url
     * @param id
     * @return the article {@link Article} if it exits, throw an ArticleNtFoundException otherwise
     */
    ArticleConsultationDTO getArticleByUrl(String url);

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
    Page<ArticleConsultationDTO> getAllArticles(Pageable page);

}
