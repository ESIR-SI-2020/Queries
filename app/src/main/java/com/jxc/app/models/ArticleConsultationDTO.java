package com.jxc.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleConsultationDTO {
    @Id
    private String id;
    private String url;
    private List<String> tags;
    private String title;
    private String body;
}