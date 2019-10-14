package com.jxc.app.models;

import java.util.List;

import lombok.*;

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
