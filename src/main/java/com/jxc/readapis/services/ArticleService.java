package com.jxc.readapis.services;



import com.jxc.readapis.dto.ArticleConsultationDTO;
import org.springframework.http.ResponseEntity;
import com.jxc.app.exceptions.RessourceNotFoundException;
import fr.esir.jxc.domain.models.User;

import java.util.List;

public interface ArticleService {

    /**
     * Method to find user articles (owned or shared) by user email
     * @param email The id of the {@link User}
     * @Param shared is a boolean which specifies if we are looking for a shared article or a owned article
     * @return A {@link ResponseEntity} that contains the list {@link ArticleConsultationDTO} of the {@link User} if he exist, throw an {@link RessourceNotFoundException} otherwise
     * @throws RessourceNotFoundException An exception with a message that display the id of the non existing {@link User}
     */
    public List<ArticleConsultationDTO> getUserArticles(String email, boolean shared);

}
