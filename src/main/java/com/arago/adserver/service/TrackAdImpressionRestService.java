package com.arago.adserver.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service("track-impression-rest-service")
public class TrackAdImpressionRestService implements TrackAdImpressionService {

    @Override
    public void trackAdImpression(String id) {
        WebClient webClient = WebClient.create();

        // POST request to the tracker impression microservice to increment ad impression count by ad id.
        webClient.post()
                .uri("http://localhost:8081/ad-impressions/{adId}", id)
                .retrieve()
                .toBodilessEntity()
                .doOnSuccess(response -> System.out.println("Impression tracked successfully for id: " + id))
                .doOnError(error -> System.err.println("Failed to track impression for id: " + id))
                .subscribe();
    }
}
