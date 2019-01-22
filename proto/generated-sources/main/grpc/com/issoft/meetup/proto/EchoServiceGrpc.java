package com.issoft.meetup.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.14.0)",
    comments = "Source: rpc.proto")
public final class EchoServiceGrpc {

  private EchoServiceGrpc() {}

  public static final String SERVICE_NAME = "EchoService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.issoft.meetup.proto.Rpc.EchoRequest,
      com.issoft.meetup.proto.Rpc.EchoResponse> getEchoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "echo",
      requestType = com.issoft.meetup.proto.Rpc.EchoRequest.class,
      responseType = com.issoft.meetup.proto.Rpc.EchoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.issoft.meetup.proto.Rpc.EchoRequest,
      com.issoft.meetup.proto.Rpc.EchoResponse> getEchoMethod() {
    io.grpc.MethodDescriptor<com.issoft.meetup.proto.Rpc.EchoRequest, com.issoft.meetup.proto.Rpc.EchoResponse> getEchoMethod;
    if ((getEchoMethod = EchoServiceGrpc.getEchoMethod) == null) {
      synchronized (EchoServiceGrpc.class) {
        if ((getEchoMethod = EchoServiceGrpc.getEchoMethod) == null) {
          EchoServiceGrpc.getEchoMethod = getEchoMethod = 
              io.grpc.MethodDescriptor.<com.issoft.meetup.proto.Rpc.EchoRequest, com.issoft.meetup.proto.Rpc.EchoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "EchoService", "echo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.issoft.meetup.proto.Rpc.EchoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.issoft.meetup.proto.Rpc.EchoResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new EchoServiceMethodDescriptorSupplier("echo"))
                  .build();
          }
        }
     }
     return getEchoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EchoServiceStub newStub(io.grpc.Channel channel) {
    return new EchoServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EchoServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EchoServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EchoServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EchoServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class EchoServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void echo(com.issoft.meetup.proto.Rpc.EchoRequest request,
        io.grpc.stub.StreamObserver<com.issoft.meetup.proto.Rpc.EchoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEchoMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getEchoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.issoft.meetup.proto.Rpc.EchoRequest,
                com.issoft.meetup.proto.Rpc.EchoResponse>(
                  this, METHODID_ECHO)))
          .build();
    }
  }

  /**
   */
  public static final class EchoServiceStub extends io.grpc.stub.AbstractStub<EchoServiceStub> {
    private EchoServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EchoServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EchoServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EchoServiceStub(channel, callOptions);
    }

    /**
     */
    public void echo(com.issoft.meetup.proto.Rpc.EchoRequest request,
        io.grpc.stub.StreamObserver<com.issoft.meetup.proto.Rpc.EchoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEchoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EchoServiceBlockingStub extends io.grpc.stub.AbstractStub<EchoServiceBlockingStub> {
    private EchoServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EchoServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EchoServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EchoServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.issoft.meetup.proto.Rpc.EchoResponse echo(com.issoft.meetup.proto.Rpc.EchoRequest request) {
      return blockingUnaryCall(
          getChannel(), getEchoMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EchoServiceFutureStub extends io.grpc.stub.AbstractStub<EchoServiceFutureStub> {
    private EchoServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EchoServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EchoServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EchoServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.issoft.meetup.proto.Rpc.EchoResponse> echo(
        com.issoft.meetup.proto.Rpc.EchoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEchoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ECHO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EchoServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EchoServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ECHO:
          serviceImpl.echo((com.issoft.meetup.proto.Rpc.EchoRequest) request,
              (io.grpc.stub.StreamObserver<com.issoft.meetup.proto.Rpc.EchoResponse>) responseObserver);
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

  private static abstract class EchoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EchoServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.issoft.meetup.proto.Rpc.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EchoService");
    }
  }

  private static final class EchoServiceFileDescriptorSupplier
      extends EchoServiceBaseDescriptorSupplier {
    EchoServiceFileDescriptorSupplier() {}
  }

  private static final class EchoServiceMethodDescriptorSupplier
      extends EchoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EchoServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EchoServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EchoServiceFileDescriptorSupplier())
              .addMethod(getEchoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
