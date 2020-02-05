package com.jxc.readapis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ArticleInfoDTO {

    private String id;
    private String url;
    private List<String> tags;
}
