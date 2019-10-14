package com.jxc.app.models;

import java.util.List;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Document(indexName = "pocket", type = "user")
public class User {

    @Id
    @NonNull
    private String email;
    @NonNull
    private String username;
    @NonNull
    private String password;

    @NonNull
    @Field(type = FieldType.Object)
    private Address address;
    private List<String> friends;

    @Field(type = FieldType.Nested)
    private List<Article> articles;

    @Field(type = FieldType.Nested)
    private List<Article> sharedArticles;

}
