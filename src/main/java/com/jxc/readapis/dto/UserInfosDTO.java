package com.jxc.readapis.dto;

import fr.esir.jxc.domain.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfosDTO {

    private String email;
    private String username;
    private Address address;
}
