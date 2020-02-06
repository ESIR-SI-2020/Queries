package com.jxc.readapis.grpc.services;

import com.jxc.readapis.exceptions.UserNotFoundException;
import com.jxc.readapis.grpc.UserInfosDTO;
import com.jxc.readapis.grpc.UserRequest;
import com.jxc.readapis.grpc.generated.UserServiceGrpc;
import com.jxc.readapis.grpc.mappers.UserMapper;
import com.jxc.readapis.services.UserService;
import fr.esir.jxc.domain.models.User;
import fr.esir.jxc.elasticsearch.repositories.UserRepository;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@GRpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    UserRepository userRepository;

    @Override
    public void findUserByEmail(UserRequest request, StreamObserver<UserInfosDTO> responseObserver) {
        Optional<User> optionalUser = this.userRepository.findById(request.getEmail());
        UserInfosDTO user = UserMapper.convertToUserInfosDTOgrpc(optionalUser);
        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }
}
