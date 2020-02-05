package com.jxc.readapis.services;

import com.jxc.readapis.dto.ArticleInfosDTO;
import com.jxc.readapis.exceptions.UserNotFoundException;
import com.jxc.readapis.mappers.ArticleMapper;
import fr.esir.jxc.domain.models.Article;
import fr.esir.jxc.elasticsearch.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ArticleServiceImp implements ArticleService{

    @Autowired
    private ArticleRepository userRepository;

    public Article save(Article article){
        return userRepository.save(article);
    }


    public List<Article> findAllArticles(){
        return this.userRepository.findAll().getContent();
    }
}
