package com.jxc.readapis.mappers;

import com.jxc.readapis.dto.ArticleConsultationDTO;
import fr.esir.jxc.domain.models.Article;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArticleMapper {

    /**
     * Method to convert a {@link User} to {@link UserInfosDTO}
     * @return a {@link UserInfosDTO}
     */
    public ArticleConsultationDTO convertToArticleConsultationDTO(Article article){
        return new ArticleConsultationDTO(article.getUrl(), article.getOwner(), article.getTags());
    }
}
