package com.jxc.readapis.grpc.generated;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: ArticleService.proto")
public final class ArticleServiceGrpc {

  private ArticleServiceGrpc() {}

  public static final String SERVICE_NAME = "ArticleService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.jxc.readapis.grpc.ArticleRequestId,
      com.jxc.readapis.grpc.Article> METHOD_FIND_ARTICLE_BY_ID =
      io.grpc.MethodDescriptor.<com.jxc.readapis.grpc.ArticleRequestId, com.jxc.readapis.grpc.Article>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "ArticleService", "findArticleByID"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.jxc.readapis.grpc.ArticleRequestId.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.jxc.readapis.grpc.Article.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.jxc.readapis.grpc.ArticleRequestOwner,
      com.jxc.readapis.grpc.ArticleList> METHOD_FIND_ARTICLES_BY_OWNER =
      io.grpc.MethodDescriptor.<com.jxc.readapis.grpc.ArticleRequestOwner, com.jxc.readapis.grpc.ArticleList>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "ArticleService", "findArticlesByOwner"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.jxc.readapis.grpc.ArticleRequestOwner.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.jxc.readapis.grpc.ArticleList.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ArticleServiceStub newStub(io.grpc.Channel channel) {
    return new ArticleServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ArticleServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ArticleServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ArticleServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ArticleServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ArticleServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void findArticleByID(com.jxc.readapis.grpc.ArticleRequestId request,
        io.grpc.stub.StreamObserver<com.jxc.readapis.grpc.Article> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_FIND_ARTICLE_BY_ID, responseObserver);
    }

    /**
     */
    public void findArticlesByOwner(com.jxc.readapis.grpc.ArticleRequestOwner request,
        io.grpc.stub.StreamObserver<com.jxc.readapis.grpc.ArticleList> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_FIND_ARTICLES_BY_OWNER, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_FIND_ARTICLE_BY_ID,
            asyncUnaryCall(
              new MethodHandlers<
                com.jxc.readapis.grpc.ArticleRequestId,
                com.jxc.readapis.grpc.Article>(
                  this, METHODID_FIND_ARTICLE_BY_ID)))
          .addMethod(
            METHOD_FIND_ARTICLES_BY_OWNER,
            asyncUnaryCall(
              new MethodHandlers<
                com.jxc.readapis.grpc.ArticleRequestOwner,
                com.jxc.readapis.grpc.ArticleList>(
                  this, METHODID_FIND_ARTICLES_BY_OWNER)))
          .build();
    }
  }

  /**
   */
  public static final class ArticleServiceStub extends io.grpc.stub.AbstractStub<ArticleServiceStub> {
    private ArticleServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ArticleServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ArticleServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ArticleServiceStub(channel, callOptions);
    }

    /**
     */
    public void findArticleByID(com.jxc.readapis.grpc.ArticleRequestId request,
        io.grpc.stub.StreamObserver<com.jxc.readapis.grpc.Article> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_FIND_ARTICLE_BY_ID, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findArticlesByOwner(com.jxc.readapis.grpc.ArticleRequestOwner request,
        io.grpc.stub.StreamObserver<com.jxc.readapis.grpc.ArticleList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_FIND_ARTICLES_BY_OWNER, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ArticleServiceBlockingStub extends io.grpc.stub.AbstractStub<ArticleServiceBlockingStub> {
    private ArticleServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ArticleServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ArticleServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ArticleServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.jxc.readapis.grpc.Article findArticleByID(com.jxc.readapis.grpc.ArticleRequestId request) {
      return blockingUnaryCall(
          getChannel(), METHOD_FIND_ARTICLE_BY_ID, getCallOptions(), request);
    }

    /**
     */
    public com.jxc.readapis.grpc.ArticleList findArticlesByOwner(com.jxc.readapis.grpc.ArticleRequestOwner request) {
      return blockingUnaryCall(
          getChannel(), METHOD_FIND_ARTICLES_BY_OWNER, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ArticleServiceFutureStub extends io.grpc.stub.AbstractStub<ArticleServiceFutureStub> {
    private ArticleServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ArticleServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ArticleServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ArticleServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.jxc.readapis.grpc.Article> findArticleByID(
        com.jxc.readapis.grpc.ArticleRequestId request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_FIND_ARTICLE_BY_ID, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.jxc.readapis.grpc.ArticleList> findArticlesByOwner(
        com.jxc.readapis.grpc.ArticleRequestOwner request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_FIND_ARTICLES_BY_OWNER, getCallOptions()), request);
    }
  }

  private static final int METHODID_FIND_ARTICLE_BY_ID = 0;
  private static final int METHODID_FIND_ARTICLES_BY_OWNER = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ArticleServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ArticleServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIND_ARTICLE_BY_ID:
          serviceImpl.findArticleByID((com.jxc.readapis.grpc.ArticleRequestId) request,
              (io.grpc.stub.StreamObserver<com.jxc.readapis.grpc.Article>) responseObserver);
          break;
        case METHODID_FIND_ARTICLES_BY_OWNER:
          serviceImpl.findArticlesByOwner((com.jxc.readapis.grpc.ArticleRequestOwner) request,
              (io.grpc.stub.StreamObserver<com.jxc.readapis.grpc.ArticleList>) responseObserver);
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

  private static final class ArticleServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.jxc.readapis.grpc.ArticleServiceOuterClass.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ArticleServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ArticleServiceDescriptorSupplier())
              .addMethod(METHOD_FIND_ARTICLE_BY_ID)
              .addMethod(METHOD_FIND_ARTICLES_BY_OWNER)
              .build();
        }
      }
    }
    return result;
  }
}
