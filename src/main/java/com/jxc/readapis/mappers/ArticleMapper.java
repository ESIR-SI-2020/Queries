package com.jxc.readapis.mappers;

import com.jxc.readapis.dto.UserInfosDTO;
import fr.esir.jxc.domain.models.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArticleMapper {

    /**
     * Method to convert a {@link Article} to {@link ArticleInfosDTO}
     * @return a {@link ArticleInfosDTO}
     */
    public ArticleInfosDTO convertToArticleInfosDTO(Article article){
        return new ArticleInfosDTO(article.getEmail(), article.getUsername(), article.getAddress());
    }
}
