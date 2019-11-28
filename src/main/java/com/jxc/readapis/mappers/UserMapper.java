package com.jxc.readapis.mappers;

import com.jxc.readapis.dto.UserInfosDTO;
import fr.esir.jxc.domain.models.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    /**
     * Method to convert a {@link User} to {@link UserInfosDTO}
     * @return a {@link UserInfosDTO}
     */
    public UserInfosDTO convertToUserInfosDTO(User user){
        return new UserInfosDTO(user.getEmail(), user.getUsername(), user.getAddress());
    }
}
