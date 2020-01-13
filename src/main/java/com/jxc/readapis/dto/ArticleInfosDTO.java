package com.jxc.readapis.dto;

import fr.esir.jxc.domain.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleInfosDTO {
    @NonNull private String id;
    @NonNull private String url;
    private String owner;
    private String sharedBy;
    private List<String> tags;
    private List<String> suggestedTags;
}
