package com.jxc.readapis.mappers;

import fr.esir.jxc.domain.models.User;
import fr.esir.jxc.domain.models.Article;
import com.jxc.readapis.dto.ArticleConsultationDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ArticleMapper {

    public static ArticleConsultationDTO toArticleConsultationDTO(Article article) {
        ArticleConsultationDTO articleConsultationDTO = new ArticleConsultationDTO(
                article.getId(), article.getUrl(), article.getOwner(), article.getSharedBy(), article.getTags(), "", ""
        );

        try {
            Document document = ArticleMapper.getDocumentByUrl(articleConsultationDTO.getUrl());
            articleConsultationDTO.setBody(document.body().toString());
            articleConsultationDTO.setTitle(document.title());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articleConsultationDTO;
    }

    public static Document getDocumentByUrl(String url) throws IOException {
        Document document = null;
        document = Jsoup.connect(url).get();
        return document;
    }
}
