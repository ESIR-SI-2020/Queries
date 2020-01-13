package com.jxc.readapis.graphql.Resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import fr.esir.jxc.domain.models.Address;
import fr.esir.jxc.domain.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserResolver implements GraphQLResolver<User> {

    public Address address(User user){
        return user.getAddress();
    }
}
