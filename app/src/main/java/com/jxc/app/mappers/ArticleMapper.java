package com.jxc.app.mappers;

import com.jxc.app.models.Article;
import com.jxc.app.models.dto.ArticleConsultationDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ArticleMapper {

    public ArticleConsultationDTO toArticleConsultationDTO(Article article) {

        ArticleConsultationDTO articleConsultationDTO = new ArticleConsultationDTO(
                article.getId(), article.getUrl(), article.getTags(), "", ""
        );

        Document document = null;
        try {
            document = this.getDocumentByUrl(articleConsultationDTO.getUrl());
            articleConsultationDTO.setBody(document.body().toString());
            articleConsultationDTO.setTitle(document.title());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return articleConsultationDTO;
    }

    public Document getDocumentByUrl(String url) throws IOException {
        Document document = null;
        document = Jsoup.connect(url).get();
        return document;
    }
}
