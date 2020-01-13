package com.jxc.readapis.graphql.Resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.jxc.readapis.dto.UserInfosDTO;
import fr.esir.jxc.domain.models.Address;
import org.springframework.stereotype.Component;

@Component
public class UserResolver implements GraphQLResolver<UserInfosDTO> {

    public Address address(UserInfosDTO user){
        return user.getAddress();
    }
}
