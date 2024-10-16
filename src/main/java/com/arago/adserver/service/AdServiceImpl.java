package com.arago.adserver.service;

import com.arago.adserver.dto.AdDto;
import com.arago.adserver.model.Ad;

import java.util.UUID;

public class AdServiceImpl implements AdService {

    @Override
    public AdDto createAd(final AdDto addDto) {

        Ad ad = new Ad();
        ad.setId(UUID.randomUUID().toString());
        ad.setTitle(addDto.getTitle());
        ad.setDescription(addDto.getDescription());
        ad.setUrl(addDto.getUrl());

        return new AdDto(ad.getId(), ad.getTitle(), ad.getDescription(), ad.getUrl());
    }
}
