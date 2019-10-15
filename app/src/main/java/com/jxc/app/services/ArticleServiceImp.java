package com.jxc.app.services;

import com.jxc.app.exceptions.ArticleNotFoundException;
import com.jxc.app.exceptions.UserNotFoundException;
import com.jxc.app.models.Article;
import com.jxc.app.models.ArticleConsultationDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleServiceImp {
    @Autowired
    UserService userService;

    public List<ArticleConsultationDTO> getUserArticles(String userId) {
        List<ArticleConsultationDTO> articles = this.userService.findUserById(userId).getArticles().stream()
                .map(this::articleToArticleConsultationDTO)
                .collect(Collectors.toList());
        return articles;
    }

    public ArticleConsultationDTO getUserArticleById(String userId, String articleId) throws UserNotFoundException, ArticleNotFoundException {
        ArticleConsultationDTO article = this.userService.findUserById(userId).getArticles().stream()
                .map(this::articleToArticleConsultationDTO)
                .filter(articleElement -> articleId.equals(articleElement.getId())).findFirst()
                .orElseThrow(() ->
                {
                    return new ArticleNotFoundException(String.format("Article with id : "+ articleId + " does not exist.", articleId));
                });
        return article;
    }

    public List<ArticleConsultationDTO> getUserSharedArticles(String userId) {
        List<ArticleConsultationDTO> articles = this.userService.findUserById(userId).getArticles().stream()
                .map(this::articleToArticleConsultationDTO)
                .collect(Collectors.toList());
        return articles;
    }

    public ArticleConsultationDTO getUserSharedArticleById(String userId, String articleId) throws UserNotFoundException, ArticleNotFoundException {
        ArticleConsultationDTO article = this.userService.findUserById(userId).getArticles().stream()
                .map(this::articleToArticleConsultationDTO)
                .filter(articleElement -> articleId.equals(articleElement.getId())).findFirst()
                .orElseThrow(() ->
                {
                    return new ArticleNotFoundException(String.format("Article with id : "+ articleId + " does not exist.", articleId));
                });
        return article;
    }

    public ArticleConsultationDTO articleToArticleConsultationDTO(Article article) {
        Document document = null;
        try {
            document = Jsoup.connect(article.getUrl()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArticleConsultationDTO articleConsultationDTO = new ArticleConsultationDTO(article.getId(), article.getUrl(), article.getTags(), document.title().toString(), document.body().toString());
        return articleConsultationDTO;
    }
}
