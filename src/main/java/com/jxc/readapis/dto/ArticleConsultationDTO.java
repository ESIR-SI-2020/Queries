package com.jxc.readapis.dto;

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
    private String owner;
    private String sharedBy;
    private List<String> tags;
    private String title;
    private String body;
}