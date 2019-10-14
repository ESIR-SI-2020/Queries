package com.jxc.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDTO {

    @Id
    private String email;
    private String username;
    private String password;

    @Field(type = FieldType.Object)
    private Address address;
}