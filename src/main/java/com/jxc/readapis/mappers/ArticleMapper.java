package com.jxc.readapis.mappers;

import com.jxc.readapis.dto.ArticleInfoDTO;
import fr.esir.jxc.domain.models.Article;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArticleMapper {

    public ArticleInfoDTO convertToArticlerInfosDTO(Article article){
        return new ArticleInfoDTO(article.getId(), article.getUrl(), article.getTags());
    }

}
