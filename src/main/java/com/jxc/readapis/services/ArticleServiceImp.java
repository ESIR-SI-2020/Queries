package com.jxc.readapis.services;

import com.jxc.readapis.dto.ArticleConsultationDTO;
import fr.esir.jxc.elasticsearch.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import fr.esir.jxc.domain.models.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImp implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public String getArticleOwner(String url) {
        return null;
    }

    @Override
    public List<ArticleConsultationDTO> getAllArticles() {
        Page<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(article -> new ArticleConsultationDTO(article.getUrl(), article.getOwner(), article.getTags()))
                .collect(Collectors.toList());
    }

    @Override
    public ArticleConsultationDTO getArticleByUrl(String url) {
        return null;
    }
}
