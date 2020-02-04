package com.jxc.readapis.services;

import com.jxc.readapis.dto.ArticleInfoDTO;
import fr.esir.jxc.domain.models.Article;
import fr.esir.jxc.domain.models.User;

import java.util.List;

public interface ArticleService {

    List<Article> getUserArticles(String owner);

    List<Article> getUserArticlesByTag(String owner, String tag);

}
