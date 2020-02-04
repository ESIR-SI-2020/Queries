package com.jxc.readapis.grpc.mappers;

import com.jxc.readapis.grpc.UserInfosDTO;
import fr.esir.jxc.domain.models.Address;
import fr.esir.jxc.domain.models.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    /**
     * Method to convert a {@link com.jxc.readapis.dto.UserInfosDTO} to {@link UserInfosDTO}
     * @return a {@link UserInfosDTO}
     */
    public UserInfosDTO convertToUserInfosDTOgrpc(com.jxc.readapis.dto.UserInfosDTO user){
        Address address = user.getAddress();
        return UserInfosDTO.newBuilder()
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setAddress(UserInfosDTO.Address.newBuilder()
                        .setStreet(address.getStreet())
                        .setComplement(address.getComplement())
                        .setPostalCode(address.getComplement())
                        .setStreetNumber(address.getStreetNumber())
                        .build())
                .build();
    }

    /**
     * Method to convert a {@link User} to {@link UserInfosDTO}
     * @return a {@link UserInfosDTO}
     */
    public UserInfosDTO convertToUserInfosDTOgrpc(User user){
        Address address = user.getAddress();
        return UserInfosDTO.newBuilder()
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setAddress(UserInfosDTO.Address.newBuilder()
                        .setStreet(address.getStreet())
                        .setComplement(address.getComplement())
                        .setPostalCode(address.getComplement())
                        .setStreetNumber(address.getStreetNumber())
                        .build())
                .build();
    }
}

