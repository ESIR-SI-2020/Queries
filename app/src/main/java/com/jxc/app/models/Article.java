package com.jxc.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Article {

    @NonNull
    private String id;
    @NonNull
    private String url;

    private List<String> tags;
    private List<String> suggestedTags;

}
