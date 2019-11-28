package com.jxc.app.services;

import com.jxc.app.exceptions.RessourceNotFoundException;
import com.jxc.app.mappers.ArticleMapper;
import com.jxc.app.models.dto.ArticleConsultationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImp implements ArticleService {
    @Autowired
    UserService userService;

    ArticleMapper articleMapper = new ArticleMapper();

    public List<ArticleConsultationDTO> getUserArticles(String email) {
        List<ArticleConsultationDTO> articles = this.userService.findUserById(email).getArticles().stream()
                .map(articleMapper::toArticleConsultationDTO)
                .collect(Collectors.toList());
        return articles;
    }

    public ArticleConsultationDTO getUserArticleById(String email, String articleId) throws RessourceNotFoundException {
        ArticleConsultationDTO article = this.userService.findUserById(email).getArticles().stream()
                .map(articleMapper::toArticleConsultationDTO)
                .filter(articleElement -> articleId.equals(articleElement.getId())).findFirst()
                .orElseThrow(() ->
                {
                    return new RessourceNotFoundException(String.format("Article with id : "+ articleId + " does not exist.", articleId));
                });
        return article;
    }

    public List<ArticleConsultationDTO> getUserSharedArticles(String email) {
        List<ArticleConsultationDTO> articles = this.userService.findUserById(email).getArticles().stream()
                .map(articleMapper::toArticleConsultationDTO)
                .collect(Collectors.toList());
        return articles;
    }

    public ArticleConsultationDTO getUserSharedArticleById(String email, String articleId) throws RessourceNotFoundException {
        ArticleConsultationDTO article = this.userService.findUserById(email).getArticles().stream()
                .map(articleMapper::toArticleConsultationDTO)
                .filter(articleElement -> articleId.equals(articleElement.getId())).findFirst()
                .orElseThrow(() ->
                {
                    return new RessourceNotFoundException(String.format("Article with id : "+ articleId + " does not exist.", articleId));
                });
        return article;
    }
}
