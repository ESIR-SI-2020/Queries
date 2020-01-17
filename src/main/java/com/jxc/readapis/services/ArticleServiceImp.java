package com.jxc.readapis.services;

import com.jxc.app.exceptions.RessourceNotFoundException;
import com.jxc.readapis.mappers.ArticleMapper;
import com.jxc.readapis.dto.ArticleConsultationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import fr.esir.jxc.elasticsearch.repositories.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class    ArticleServiceImp implements ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    ArticleMapper articleMapper;

    public List<ArticleConsultationDTO> getUserArticles(String email, boolean shared) {
        List<ArticleConsultationDTO> articles = new ArrayList<>();
        if (shared) {
            return this.listSharedArticles(email);
        } else {
            return this.listNotSharedArticles(email);
        }
    }
    // facing issue "No query lookup strategy defined" when using articleRepository.findByOwner(email) or
    // findBySharedBy(email)... so I made use of articleRepository.findAll() and then filtered the results by
    // sharedBy or by owner
    private List<ArticleConsultationDTO> listNotSharedArticles(String email) throws RessourceNotFoundException {
        return this.articleRepository.findAll().stream()
                .filter(elt -> elt.getOwner().equals(email))
                .map(ArticleMapper::toArticleConsultationDTO)
                .collect(Collectors.toList());
    }
    private List<ArticleConsultationDTO> listSharedArticles(String email) throws RessourceNotFoundException {
        return this.articleRepository.findAll().stream()
                .filter(elt -> elt.getSharedBy().equals(email))
                .map(ArticleMapper::toArticleConsultationDTO)
                .collect(Collectors.toList());
    }
}
