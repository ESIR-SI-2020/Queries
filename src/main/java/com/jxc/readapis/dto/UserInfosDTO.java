package com.jxc.readapis.dto;

import com.jxc.dbmanager.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfosDTO {

    private String email;
    private String username;
    private Address address;
}
