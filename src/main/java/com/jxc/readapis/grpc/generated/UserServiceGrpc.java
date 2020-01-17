package com.jxc.readapis.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: UserService.proto")
public final class UserServiceGrpc {

  private UserServiceGrpc() {}

  public static final String SERVICE_NAME = "UserService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.jxc.readapis.grpc.UserRequest,
      com.jxc.readapis.grpc.UserInfosDTO> METHOD_FIND_USER_BY_EMAIL =
      io.grpc.MethodDescriptor.<com.jxc.readapis.grpc.UserRequest, com.jxc.readapis.grpc.UserInfosDTO>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "UserService", "findUserByEmail"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.jxc.readapis.grpc.UserRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.jxc.readapis.grpc.UserInfosDTO.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserServiceStub newStub(io.grpc.Channel channel) {
    return new UserServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class UserServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *rpc findAllUsers(google.protobuf.Empty) returns (stream UserInfosDTO);
     * </pre>
     */
    public void findUserByEmail(com.jxc.readapis.grpc.UserRequest request,
        io.grpc.stub.StreamObserver<com.jxc.readapis.grpc.UserInfosDTO> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_FIND_USER_BY_EMAIL, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_FIND_USER_BY_EMAIL,
            asyncUnaryCall(
              new MethodHandlers<
                com.jxc.readapis.grpc.UserRequest,
                com.jxc.readapis.grpc.UserInfosDTO>(
                  this, METHODID_FIND_USER_BY_EMAIL)))
          .build();
    }
  }

  /**
   */
  public static final class UserServiceStub extends io.grpc.stub.AbstractStub<UserServiceStub> {
    private UserServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *rpc findAllUsers(google.protobuf.Empty) returns (stream UserInfosDTO);
     * </pre>
     */
    public void findUserByEmail(com.jxc.readapis.grpc.UserRequest request,
        io.grpc.stub.StreamObserver<com.jxc.readapis.grpc.UserInfosDTO> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_FIND_USER_BY_EMAIL, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UserServiceBlockingStub extends io.grpc.stub.AbstractStub<UserServiceBlockingStub> {
    private UserServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *rpc findAllUsers(google.protobuf.Empty) returns (stream UserInfosDTO);
     * </pre>
     */
    public com.jxc.readapis.grpc.UserInfosDTO findUserByEmail(com.jxc.readapis.grpc.UserRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_FIND_USER_BY_EMAIL, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserServiceFutureStub extends io.grpc.stub.AbstractStub<UserServiceFutureStub> {
    private UserServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *rpc findAllUsers(google.protobuf.Empty) returns (stream UserInfosDTO);
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.jxc.readapis.grpc.UserInfosDTO> findUserByEmail(
        com.jxc.readapis.grpc.UserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_FIND_USER_BY_EMAIL, getCallOptions()), request);
    }
  }

  private static final int METHODID_FIND_USER_BY_EMAIL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIND_USER_BY_EMAIL:
          serviceImpl.findUserByEmail((com.jxc.readapis.grpc.UserRequest) request,
              (io.grpc.stub.StreamObserver<com.jxc.readapis.grpc.UserInfosDTO>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class UserServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.jxc.readapis.grpc.UserServiceOuterClass.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserServiceDescriptorSupplier())
              .addMethod(METHOD_FIND_USER_BY_EMAIL)
              .build();
        }
      }
    }
    return result;
  }
}
