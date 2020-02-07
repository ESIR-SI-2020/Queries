package com.jxc.readapis.services;

import com.jxc.readapis.dto.ArticleInfosDTO;

import java.util.List;

public interface ArticleService {
    List<ArticleInfosDTO> getAllSharedArticles(String email);
}
