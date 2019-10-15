package com.jxc.app.services;

import com.jxc.app.exceptions.UserNotFoundException;
import com.jxc.app.models.Article;
import com.jxc.app.models.ArticleConsultationDTO;
import com.jxc.app.models.User;
import com.jxc.app.repositories.UserRepository;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public User findUserById(String id){
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id : "+ id + " does not exist."));
    }

    public List<User> findAllUsers(){
        return this.userRepository.findAll().getContent();
    }

    public ArticleConsultationDTO articleToArticleConsultationDTO(Article article) {
        Document document = null;
        try {
            document = Jsoup.connect(article.getUrl()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArticleConsultationDTO articleConsultationDTO = new ArticleConsultationDTO(article.getId(), article.getUrl(), article.getTags(), document.title().toString(), document.body().toString());
        return articleConsultationDTO;
    }

}
