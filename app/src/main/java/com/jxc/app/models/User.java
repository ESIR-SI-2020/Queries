package com.jxc.app.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "pocket", type = "user")
public class User {

    @Id
    private String id;
    private String username;
    private String email;
    private String photoUrl;
    private String bio;

    @Field(type = FieldType.Object)
    private Address address;
    private List<String> friendsId;

}
