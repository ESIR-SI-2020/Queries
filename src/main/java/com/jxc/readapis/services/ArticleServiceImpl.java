package com.jxc.readapis.services;

import com.jxc.readapis.dto.ArticleInfosDTO;
import com.jxc.readapis.mappers.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("elasticsearchArticlesService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticlesReadService articlesReadService;


    @Override
    public List<ArticleInfosDTO> getAllSharedArticles(String email) {
       return this.articlesReadService.getAllArticlesSharedBy(email)
                .stream()
                .map(ArticleMapper::convertToArticleInfosDTO)
                .collect(Collectors.toList());

    }
}
