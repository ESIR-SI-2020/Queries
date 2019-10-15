package com.jxc.app.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleConsultationDTO {
    @NonNull
    private String id;
    @NonNull
    private String url;
    private List<String> tags;
    private String title;
    private String body;
}