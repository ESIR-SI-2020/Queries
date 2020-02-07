package com.jxc.readapis.mappers;


import com.jxc.readapis.dto.ArticleInfosDTO;
import fr.esir.jxc.domain.models.Article;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArticleMapper {


    public ArticleInfosDTO convertToArticleInfosDTO(Article article){
        return new ArticleInfosDTO(article.getId(),article.getUrl(),article.getTags(),article.getSuggestedTags());
    }


}
