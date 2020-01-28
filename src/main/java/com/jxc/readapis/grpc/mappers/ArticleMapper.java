package com.jxc.readapis.grpc.mappers;


import com.jxc.readapis.grpc.Article;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

@UtilityClass
public class ArticleMapper {

    /**
     * Method to convert a {@link fr.esir.jxc.domain.models.Article} to {@link Article}
     * @return a {@link Article}
     */
    public Article convertToArticle(fr.esir.jxc.domain.models.Article article){
        return Article.newBuilder()
                .setId(article.getId())
                .setUrl(article.getUrl())
                .setOwner(article.getOwner())
                .addAllTags(article.getTags())
                .addAllSuggestedTags(article.getSuggestedTags())
                .setSharedBy(article.getSharedBy())
                .build();
    }
}
