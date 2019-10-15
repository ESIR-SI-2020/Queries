package com.jxc.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    private String id;
    private String url;
    private List<String> tags;
    private List<String> suggestedTags;
}