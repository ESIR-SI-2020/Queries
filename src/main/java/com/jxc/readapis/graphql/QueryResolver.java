package com.jxc.readapis.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jxc.readapis.exceptions.ArticleNotFoundException;
import com.jxc.readapis.exceptions.UserNotFoundException;
import fr.esir.jxc.domain.models.Article;
import fr.esir.jxc.domain.models.User;
import fr.esir.jxc.elasticsearch.repositories.ArticleRepository;
import fr.esir.jxc.elasticsearch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ArticleRepository articleRepository;

    public User findUserByEmail(String email){
        return userRepository.findById(email).orElseThrow(() -> new UserNotFoundException("User with email : "+ email + " does not exist."));
    }

    public List<User> findAllUsers(){
        return userRepository.findAll().getContent();
    }

    public Article findArticleById(String id){
        return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException("Article with id : "+ id + " does not exist."));
    }

    public List<Article> findAllArticles(){
        return articleRepository.findAll().getContent();
    }
}
