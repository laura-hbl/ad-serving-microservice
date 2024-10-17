package com.arago.tracking;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: tracking.proto")
public final class TrackingServiceGrpc {

  private TrackingServiceGrpc() {}

  public static final String SERVICE_NAME = "tracking.TrackingService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.arago.tracking.TrackAdImpressionRequest,
      com.arago.tracking.TrackAdImpressionResponse> METHOD_TRACK_AD_IMPRESSION =
      io.grpc.MethodDescriptor.<com.arago.tracking.TrackAdImpressionRequest, com.arago.tracking.TrackAdImpressionResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "tracking.TrackingService", "TrackAdImpression"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.arago.tracking.TrackAdImpressionRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.arago.tracking.TrackAdImpressionResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TrackingServiceStub newStub(io.grpc.Channel channel) {
    return new TrackingServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TrackingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TrackingServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TrackingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TrackingServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TrackingServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void trackAdImpression(com.arago.tracking.TrackAdImpressionRequest request,
        io.grpc.stub.StreamObserver<com.arago.tracking.TrackAdImpressionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_TRACK_AD_IMPRESSION, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_TRACK_AD_IMPRESSION,
            asyncUnaryCall(
              new MethodHandlers<
                com.arago.tracking.TrackAdImpressionRequest,
                com.arago.tracking.TrackAdImpressionResponse>(
                  this, METHODID_TRACK_AD_IMPRESSION)))
          .build();
    }
  }

  /**
   */
  public static final class TrackingServiceStub extends io.grpc.stub.AbstractStub<TrackingServiceStub> {
    private TrackingServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TrackingServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackingServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TrackingServiceStub(channel, callOptions);
    }

    /**
     */
    public void trackAdImpression(com.arago.tracking.TrackAdImpressionRequest request,
        io.grpc.stub.StreamObserver<com.arago.tracking.TrackAdImpressionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_TRACK_AD_IMPRESSION, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TrackingServiceBlockingStub extends io.grpc.stub.AbstractStub<TrackingServiceBlockingStub> {
    private TrackingServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TrackingServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackingServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TrackingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.arago.tracking.TrackAdImpressionResponse trackAdImpression(com.arago.tracking.TrackAdImpressionRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_TRACK_AD_IMPRESSION, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TrackingServiceFutureStub extends io.grpc.stub.AbstractStub<TrackingServiceFutureStub> {
    private TrackingServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TrackingServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackingServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TrackingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.arago.tracking.TrackAdImpressionResponse> trackAdImpression(
        com.arago.tracking.TrackAdImpressionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_TRACK_AD_IMPRESSION, getCallOptions()), request);
    }
  }

  private static final int METHODID_TRACK_AD_IMPRESSION = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TrackingServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TrackingServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TRACK_AD_IMPRESSION:
          serviceImpl.trackAdImpression((com.arago.tracking.TrackAdImpressionRequest) request,
              (io.grpc.stub.StreamObserver<com.arago.tracking.TrackAdImpressionResponse>) responseObserver);
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

  private static final class TrackingServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.arago.tracking.Tracking.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TrackingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TrackingServiceDescriptorSupplier())
              .addMethod(METHOD_TRACK_AD_IMPRESSION)
              .build();
        }
      }
    }
    return result;
  }
}
