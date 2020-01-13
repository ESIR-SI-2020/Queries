package com.jxc.readapis.services;

import com.jxc.readapis.dto.UserInfosDTO;
import fr.esir.jxc.domain.models.User;

import java.util.List;

public interface ArticleService {

    /**
     * Method to save a {@link Article}, only here for testing purpose
     * @param user The {@link Article} to save
     * @return The article saved
     */
    Article save(Article article);

    /**
     * Method to find a {@link Article} by id
     * @param email the email of the {@link Article}
     * @return the {@link Article } if he exist, throw an {@link com.jxc.readapis.exceptions.ArticleNotFoundException} otherwise
     */
    ArticleInfosDTO getArticleById(String id);
}
