package com.arago.adserver.service;

import com.arago.adserver.dto.AdDto;
import com.arago.adserver.exception.ResourceNotFoundException;
import com.arago.adserver.model.Ad;
import com.arago.adserver.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Implementation of the AdService interface.
 * It handles ads.
 */
@Service
public class AdServiceImpl implements AdService {

    private final TrackAdImpressionService trackAdImpressionService;
    private final AdRepository adRepository;

    /**
     * Constructor of AdServiceImpl.
     *
     * @param trackAdImpressionService the service to track ad impressions
     * @param adRepository the repository for managing ad data
     */
    @Autowired
    public AdServiceImpl(@Qualifier("track-impression-grpc-service") TrackAdImpressionService trackAdImpressionService, AdRepository adRepository) {
        this.trackAdImpressionService = trackAdImpressionService;
        this.adRepository = adRepository;
    }

    /**
     * Creates a new ad.
     *
     * @param adDto the advertisement data transfer object containing ad info
     * @return AdDto containing the created ad info
     */
    @Override
    public AdDto createAd(final AdDto adDto) {

        Ad ad = new Ad(adDto.getTitle(), adDto.getDescription(), adDto.getUrl());
        Ad adSaved = adRepository.save(ad);

        return new AdDto(adSaved.getId(), adSaved.getTitle(), adSaved.getDescription(), adSaved.getUrl());
    }

    /**
     * Retrieves an ad by its id.
     *
     * @param id the id of the ad to retrieve
     * @return AdDto containing the ad info
     * @throws ResourceNotFoundException if no advertisement is found with the specified id
     */
    @Override
    public AdDto getAd(final String id) {

        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("No ad registered with this id: " + id));

        return new AdDto(ad.getId(), ad.getTitle(), ad.getDescription(), ad.getUrl());
    }

    /**
     * Serves an ad by its id and tracks the impression.
     *
     * @param id the id of the ad to serve
     * @return the URL of the ad if found; or an error message if an exception occurs
     */
    @Override
    public String serveAd(final String id) {

        AdDto ad;

        try {
            ad = getAd(id);
            trackAdImpressionService.trackAdImpression(id);
        } catch (Exception e) {
            return e.getMessage();
        }

        return ad.getUrl();
    }
}
