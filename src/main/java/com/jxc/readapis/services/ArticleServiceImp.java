package com.jxc.readapis.services;

import com.jxc.readapis.dto.ArticleConsultationDTO;
import com.jxc.readapis.mappers.ArticleMapper;
import com.jxc.readapis.repositories.EsArticleRepository;
import fr.esir.jxc.elasticsearch.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import fr.esir.jxc.domain.models.Article;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImp implements ArticleService {

    @Autowired
    private EsArticleRepository articleRepository;

    @Override
    public List<ArticleConsultationDTO> getArticlesByOwner(String owner) {
        List<Article> articles = this.articleRepository.findArticlesByOwner(owner);

        return articles.stream()
                    .map(article -> ArticleMapper.mapArticleToArticleDTO(article))
                    .collect(Collectors.toList());
    }

    @Override
    public List<ArticleConsultationDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(article -> ArticleMapper.mapArticleToArticleDTO(article))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ArticleConsultationDTO> getArticleById(String id) {
        return Optional.ofNullable(articleRepository.findArticleById(id))
            .map(article -> ArticleMapper.mapArticleToArticleDTO(article));
    }

}
