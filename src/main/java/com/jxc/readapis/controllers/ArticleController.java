package com.jxc.readapis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.jxc.app.exceptions.RessourceNotFoundException;
import fr.esir.jxc.domain.models.User;
import fr.esir.jxc.domain.models.Article;
import com.jxc.readapis.dto.ArticleConsultationDTO;
import com.jxc.readapis.services.ArticleService;

@RestController
@RequestMapping(value = "/api/v1/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleController {

    @Autowired
    ArticleService articleService;
    String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    Pattern pattern = Pattern.compile(emailRegex);
    /**
     * Method to find user articles by user email
     * @param email The id of the {@link User}
     * @return A {@link ResponseEntity} that contains the list {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link RessourceNotFoundException} otherwise
     * @throws RessourceNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    @GetMapping("?owner={email}")
    public ResponseEntity<List<ArticleConsultationDTO>> getUserArticles(@PathVariable String email) throws RessourceNotFoundException, IOException {
        List<ArticleConsultationDTO> articles = new ArrayList<>();
        if (this.pattern.matcher(email).matches()) {
            // verifie que l'e-mail est bien un email
            articles = this.articleService.getUserArticles(email, false);
        }
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
    /**
     * Method to find user shared articles by user email
     * @param email The id of the {@link User}
     * @return A {@link ResponseEntity} that contains the list of {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link RessourceNotFoundException} otherwise
     * @throws RessourceNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    @GetMapping("/shared/?owner={email}")
    public ResponseEntity<List<ArticleConsultationDTO>> getUserSharedArticles(@PathVariable String email) throws RessourceNotFoundException, IOException {
        List<ArticleConsultationDTO> sharedArticles = new ArrayList<>();
        if (this.pattern.matcher(email).matches()) {
            sharedArticles = this.articleService.getUserArticles(email, true);
        }
        return new ResponseEntity<>(sharedArticles, HttpStatus.OK);
    }
}
