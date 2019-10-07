package com.jxc.app.repositories;

import com.jxc.app.models.Address;
import com.jxc.app.models.User;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/*
This class is a mock repository
*/
@Component
public class UserRepository {

    private List<User> userList;

    public UserRepository(){
        Address address0 = new Address("postalCode0", "street0", 0, "complement0");
        Address address1 = new Address("postalCode1", "street1", 1, "complement1");
        Address address2 = new Address("postalCode2", "street2", 2, "complement2");

        User user_zero = new User("0", "user0", "user0@mail.com", "http://www.photo.com/0.png", "bio0", address0, Arrays.asList("1", "2"));
        User user_one = new User("1", "user1", "user1@mail.com", "http://www.photo.com/1.png", "bio1", address1, Arrays.asList("0", "2"));
        User user_two = new User("2", "user2", "user2@mail.com", "http://www.photo.com/2.png", "bio2", address2, Arrays.asList("0", "1"));

        userList = new ArrayList<>();
        userList.add(user_zero);
        userList.add(user_one);
        userList.add(user_two);
    }

    public Optional<User> findById(String id){
        for (User user:userList) {
            if(user.getId().equals(id)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public List<User> findAll(){
        return this.userList;
    }
}
