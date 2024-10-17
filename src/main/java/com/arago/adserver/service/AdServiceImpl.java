package com.arago.adserver.service;

import com.arago.adserver.dto.AdDto;
import com.arago.adserver.exception.ResourceNotFoundException;
import com.arago.adserver.model.Ad;
import com.arago.adserver.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;

    @Autowired
    public AdServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public AdDto createAd(final AdDto addDto) {

        Ad ad = new Ad(UUID.randomUUID().toString(), addDto.getTitle(), addDto.getDescription(), addDto.getUrl());
        Ad adSaved = adRepository.save(ad);

        return new AdDto(adSaved.getId(), adSaved.getTitle(), adSaved.getDescription(), adSaved.getUrl());
    }

    @Override
    public AdDto getAd(final String id) {

        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("No ad registered with this id"));

        return new AdDto(ad.getId(), ad.getTitle(), ad.getDescription(), ad.getUrl());
    }
}
