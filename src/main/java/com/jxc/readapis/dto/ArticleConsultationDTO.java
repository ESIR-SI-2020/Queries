package com.jxc.readapis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ArticleConsultationDTO {

    private String url;
    private String owner;
    private List<String> tags;
    //private List<String> suggestedTags;
}

