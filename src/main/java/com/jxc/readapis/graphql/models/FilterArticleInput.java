package com.jxc.readapis.graphql.models;

import lombok.Data;


@Data
public class FilterArticleInput {
    private String id;
    private String url;
    private String owner;
    private String sharedBy;
    private String tag;
    private String suggestedTag;
}
