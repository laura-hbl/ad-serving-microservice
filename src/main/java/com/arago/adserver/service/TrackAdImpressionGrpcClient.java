package com.arago.adserver.service;

import com.arago.tracking.TrackAdImpressionRequest;
import com.arago.tracking.TrackAdImpressionResponse;
import com.arago.tracking.TrackingServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ManagedChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("track-impression-grpc-service")
public class TrackAdImpressionGrpcClient implements TrackAdImpressionService {

    private static final Logger LOGGER = LogManager.getLogger(TrackAdImpressionGrpcClient.class);

    @Value("${grpc.server.host}")
    private String grpcHost;

    @Value("${grpc.server.port}")
    private int grpcPort;

    @Override
    public void trackAdImpression(String id) {
         ManagedChannel channel = ManagedChannelBuilder.forAddress(grpcHost, grpcPort)
                 .usePlaintext()
                 .build();
         TrackingServiceGrpc.TrackingServiceBlockingStub trackingStub = TrackingServiceGrpc.newBlockingStub(channel);

         try {
            TrackAdImpressionRequest request = TrackAdImpressionRequest.newBuilder()
                    .setAdId(id)
                    .build();
            TrackAdImpressionResponse response = trackingStub.trackAdImpression(request);
            LOGGER.info("Impression tracked successfully for ad id: {} with message {}", id, response.getMessage());
         } catch (Exception e) {
             String error = "Failed to track impression for ad id: %s due to: %s. Ensure tracking service has been started".formatted(id, e.getMessage());
             LOGGER.error(error, e);
             throw new RuntimeException(error);
         } finally {
             channel.shutdownNow();
         }
    }
}
