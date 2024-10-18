package com.arago.adserver.service;

import com.arago.tracking.TrackAdImpressionRequest;
import com.arago.tracking.TrackAdImpressionResponse;
import com.arago.tracking.TrackingServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ManagedChannel;

import org.springframework.stereotype.Service;

@Service("track-impression-grpc-service")
public class TrackAdImpressionGrpcClient implements TrackAdImpressionService {

    @Override
    public void trackAdImpression(String id) {
         ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                 .usePlaintext()
                 .build();
         TrackingServiceGrpc.TrackingServiceBlockingStub trackingStub = TrackingServiceGrpc.newBlockingStub(channel);

         try {
            TrackAdImpressionRequest request = TrackAdImpressionRequest.newBuilder()
                    .setAdId(id)
                    .build();
            TrackAdImpressionResponse response = trackingStub.trackAdImpression(request);

            System.out.println("Impression tracked successfully for id: " + id);
         } catch (Exception e) {
            System.err.println("Failed to track impression for id: " + id + " due to: " + e.getMessage());
         }

         channel.shutdownNow();
    }
}
