package com.jxc.readapis.mappers;

import com.jxc.readapis.dto.ArticleInfosDTO;
import fr.esir.jxc.domain.models.Article;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArticleMapper {

    /**
     * Method to convert a {@link Article} to {@link ArticleInfosDTO}
     * @return a {@link ArticleInfosDTO}
     */
    public ArticleInfosDTO convertToArticleInfosDTO(Article article){
        return new ArticleInfosDTO(article.getId(), article.getUrl(), article.getOwner(), article.getSharedBy(), article.getTags(), article.getSuggestedTags());
    }
}
