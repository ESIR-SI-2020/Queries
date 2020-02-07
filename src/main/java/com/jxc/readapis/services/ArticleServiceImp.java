package com.jxc.readapis.services;

import com.jxc.readapis.dto.ArticleConsultationDTO;
import com.jxc.readapis.exceptions.ArticleNotFoundException;
import com.jxc.readapis.mappers.ArticleMapper;
import fr.esir.jxc.domain.models.Article;
import fr.esir.jxc.elasticsearch.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImp implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article save(Article article){
        return articleRepository.save(article);
    }

    public String getArticleOwner(String url) {
        return null;
    }


    public Page<ArticleConsultationDTO> getAllArticles(Pageable page) {
        List<ArticleConsultationDTO> articleConsultationDTO = new ArrayList<>();
        Page<Article> articles = articleRepository.findAll(page);
        articleRepository.findAll(page).getContent().forEach(article -> articleConsultationDTO.add(ArticleMapper.convertToArticleConsultationDTO(article)));
        return new PageImpl<>(articleConsultationDTO,page,articles.getTotalElements());
    }


    public ArticleConsultationDTO getArticleByUrl(String url) {
        return null;
    }
}
