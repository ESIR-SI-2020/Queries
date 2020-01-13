package com.jxc.readapis.services;

import com.jxc.readapis.dto.ArticleInfoDTO;
import com.jxc.readapis.mappers.ArticleMapper;
import fr.esir.jxc.domain.models.Article;
import fr.esir.jxc.domain.models.User;
import fr.esir.jxc.elasticsearch.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImp implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public List<ArticleInfoDTO> getUserArticles(String email) {
        List<Article> articles = articleRepository.findByOwner(email).getContent();
        return articles.stream().map(ArticleMapper::convertToArticlerInfosDTO).collect(Collectors.toList());
    }

    @Override
    public List<ArticleInfoDTO> getUserArticlesByTag(String email, String tag) {
        List<Article> articles = articleRepository.findByOwner(email).getContent();
        return articles.stream().filter(article -> article.getTags().contains(tag))
                .map(ArticleMapper::convertToArticlerInfosDTO).collect(Collectors.toList());
    }
}
