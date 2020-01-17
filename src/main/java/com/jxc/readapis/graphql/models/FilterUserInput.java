package com.jxc.readapis.graphql.models;

import fr.esir.jxc.domain.models.Address;
import lombok.Data;

@Data
public class FilterUserInput {
    private String email;
    private String username;
    private Address address;
    private String friend;
}
