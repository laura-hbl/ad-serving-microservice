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

/**
 * gRPC client for tracking ad impressions.
 * This class interacts with a gRPC service to record impressions for ads.
 */
@Service("track-impression-grpc-service")
public class TrackAdImpressionGrpcClient implements TrackAdImpressionService {

    private static final Logger LOGGER = LogManager.getLogger(TrackAdImpressionGrpcClient.class);

    @Value("${grpc.server.host}")
    private String grpcHost;

    @Value("${grpc.server.port}")
    private int grpcPort;

    /**
     * Tracks an ad impression by sending a request to the gRPC tracking service.
     *
     * @param id the ID of the ad whose impression is to be tracked
     * @throws RuntimeException if tracking the impression fails due to an exception
     */
    @Override
    public void trackAdImpression(String id) {

        // gRPC channel to communicates with the tracking service
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
