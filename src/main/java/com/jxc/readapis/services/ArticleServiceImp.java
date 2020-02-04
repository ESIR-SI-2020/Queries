package com.jxc.readapis.services;

import com.jxc.readapis.dto.ArticleInfosDTO;
import com.jxc.readapis.exceptions.ArticleNotFoundException;
import com.jxc.readapis.mappers.ArticleMapper;
import fr.esir.jxc.domain.models.Article;
import fr.esir.jxc.elasticsearch.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImp implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public Article save(Article article){
        return articleRepository.save(article);
    }

    public ArticleInfosDTO getArticleById(String id){
        Article article = (this.articleRepository.findById(id))
                .orElseThrow(() -> new ArticleNotFoundException("Article with id : "+ id + " does not exist."));
        return ArticleMapper.convertToArticleInfosDTO(article);
    }
}
