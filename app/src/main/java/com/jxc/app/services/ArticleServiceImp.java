package com.jxc.app.services;

import com.jxc.app.exceptions.ArticleNotFoundException;
import com.jxc.app.exceptions.UserNotFoundException;
import com.jxc.app.mappers.ArticleMapper;
import com.jxc.app.models.Article;
import com.jxc.app.models.dto.ArticleConsultationDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleServiceImp implements ArticleService {
    @Autowired
    UserService userService;

    @Autowired
    ArticleMapper articleMapper;

    public List<ArticleConsultationDTO> getUserArticles(String email) {
        List<ArticleConsultationDTO> articles = this.userService.findUserById(email).getArticles().stream()
                .map(articleMapper::toArticleConsultationDTO)
                .collect(Collectors.toList());
        return articles;
    }

    public ArticleConsultationDTO getUserArticleById(String email, String articleId) throws UserNotFoundException, ArticleNotFoundException {
        ArticleConsultationDTO article = this.userService.findUserById(email).getArticles().stream()
                .map(articleMapper::toArticleConsultationDTO)
                .filter(articleElement -> articleId.equals(articleElement.getId())).findFirst()
                .orElseThrow(() ->
                {
                    return new ArticleNotFoundException(String.format("Article with id : "+ articleId + " does not exist.", articleId));
                });
        return article;
    }

    public List<ArticleConsultationDTO> getUserSharedArticles(String email) {
        List<ArticleConsultationDTO> articles = this.userService.findUserById(email).getArticles().stream()
                .map(articleMapper::toArticleConsultationDTO)
                .collect(Collectors.toList());
        return articles;
    }

    public ArticleConsultationDTO getUserSharedArticleById(String email, String articleId) throws UserNotFoundException, ArticleNotFoundException {
        ArticleConsultationDTO article = this.userService.findUserById(email).getArticles().stream()
                .map(articleMapper::toArticleConsultationDTO)
                .filter(articleElement -> articleId.equals(articleElement.getId())).findFirst()
                .orElseThrow(() ->
                {
                    return new ArticleNotFoundException(String.format("Article with id : "+ articleId + " does not exist.", articleId));
                });
        return article;
    }
}
