package com.jxc.app.mapper;

import com.jxc.app.models.User;
import com.jxc.app.models.UserInfosDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    /**
     * Method to convert a {@link User} to {@link UserInfosDTO}
     * @return a {@link UserInfosDTO}
     */
    public UserInfosDTO ConvertToUserInfosDTO(User user){
        return new UserInfosDTO(user.getEmail(), user.getUsername(), user.getAddress());
    }
}

