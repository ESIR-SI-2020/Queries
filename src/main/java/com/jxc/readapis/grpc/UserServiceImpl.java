package com.jxc.readapis.grpc;

import com.jxc.readapis.dto.UserInfosDTO;
import com.jxc.readapis.services.UserService;
import fr.esir.jxc.domain.models.Address;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    UserService userService;

    @Override
    public void findUserByEmail(UserRequest request, StreamObserver<com.jxc.readapis.grpc.UserInfosDTO> responseObserver) {
        UserInfosDTO user = userService.getUserByEmail(request.getEmail());
        Address address = user.getAddress();
        com.jxc.readapis.grpc.UserInfosDTO response = com.jxc.readapis.grpc.UserInfosDTO.newBuilder()
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setAddress(com.jxc.readapis.grpc.UserInfosDTO.Address.newBuilder()
                        .setStreet(address.getStreet())
                        .setComplement(address.getComplement())
                        .setPostalCode(address.getComplement())
                        .setStreetNumber(address.getStreetNumber())
                        .build())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
