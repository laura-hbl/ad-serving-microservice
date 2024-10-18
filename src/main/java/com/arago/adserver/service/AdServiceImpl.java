package com.arago.adserver.service;

import com.arago.adserver.dto.AdDto;
import com.arago.adserver.exception.ResourceNotFoundException;
import com.arago.adserver.model.Ad;
import com.arago.adserver.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AdServiceImpl implements AdService {

    private final TrackAdImpressionService trackAdImpressionService;
    private final AdRepository adRepository;

    @Autowired
    public AdServiceImpl(@Qualifier("track-impression-grpc-service") TrackAdImpressionService trackAdImpressionService, AdRepository adRepository) {
        this.trackAdImpressionService = trackAdImpressionService;
        this.adRepository = adRepository;
    }

    @Override
    public AdDto createAd(final AdDto adDto) {

        Ad ad = new Ad(adDto.getTitle(), adDto.getDescription(), adDto.getUrl());
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
        trackAdImpressionService.trackAdImpression(id);

        return ad.getUrl();
    }
}
