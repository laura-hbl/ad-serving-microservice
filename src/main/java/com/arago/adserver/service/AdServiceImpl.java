package com.arago.adserver.service;

import com.arago.adserver.dto.AdDto;
import com.arago.adserver.exception.ResourceNotFoundException;
import com.arago.adserver.model.Ad;
import com.arago.adserver.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;

    @Autowired
    public AdServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public AdDto createAd(final AdDto addDto) {

        Ad ad = new Ad(addDto.getTitle(), addDto.getDescription(), addDto.getUrl());
        Ad adSaved = adRepository.save(ad);

        return new AdDto(adSaved.getId(), adSaved.getTitle(), adSaved.getDescription(), adSaved.getUrl());
    }

    @Override
    public AdDto getAd(final String id) {

        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("No ad registered with this id: " + id));

        return new AdDto(ad.getId(), ad.getTitle(), ad.getDescription(), ad.getUrl());
    }

    @Override
    public String serveAd(final String id) {

        AdDto ad = getAd(id);
        trackAdImpression(id);

        return ad.getUrl();
    }

    private void trackAdImpression(final String id) {

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
