package com.jxc.readapis.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import fr.esir.jxc.domain.models.Article;
import fr.esir.jxc.domain.models.User;
import fr.esir.jxc.elasticsearch.repositories.ArticleRepository;
import fr.esir.jxc.elasticsearch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ArticleRepository articleRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public Article createArticle(Article article){
        return articleRepository.save(article);
    }
}
