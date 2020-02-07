package com.jxc.readapis.mappers;

import com.jxc.readapis.dto.ArticleConsultationDTO;
import fr.esir.jxc.domain.models.Article;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArticleMapper {

    public static ArticleConsultationDTO mapArticleToArticleDTO(Article article){
        return new ArticleConsultationDTO(article.getId(), article.getUrl(), article.getOwner(), article.getTags());
    }
}
