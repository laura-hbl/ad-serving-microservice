package com.arago.adserver.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * REST service client for tracking ad impressions.
 * It communicates with impression tracker microservice to track ad impressions using HTTP requests.
 */
@Service("track-impression-rest-service")
public class TrackAdImpressionRestService implements TrackAdImpressionService {

    private final WebClient webClient;
    private static final Logger LOGGER = LogManager.getLogger(TrackAdImpressionRestService.class);

    @Value("${impression.tracker.service.url}")
    private String impressionTrackerUrl;

    /**
     * Constructor of TrackAdImpressionRestService.
     *
     * @param webClientBuilder the builder for creating WebClient instances
     */
    public TrackAdImpressionRestService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(impressionTrackerUrl).build();
    }

    /**
     * Tracks an ad impression by sending a POST request to the impression tracking service.
     *
     * @param id the id of the ad whose impression is to be tracked
     */
    @Override
    public void trackAdImpression(String id) {

        // POST request to the tracker impression microservice to increment ad impression count by ad id.
        webClient.post()
                .uri("/ad-impressions/{adId}", id)
                .retrieve()
                .toBodilessEntity()
                .doOnSuccess(response -> LOGGER.info("Impression tracked successfully for ad id: {}", id))
                .doOnError(error ->
                        LOGGER.error("An unexpected error occurred while tracking impression for ad id: {}", id, error))
                .subscribe();
    }
}
