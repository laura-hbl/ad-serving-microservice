package com.arago.adserver.service;

import com.arago.adserver.dto.AdDto;
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

        Ad ad = new Ad();
        ad.setId(UUID.randomUUID().toString());
        ad.setTitle(addDto.getTitle());
        ad.setDescription(addDto.getDescription());
        ad.setUrl(addDto.getUrl());

        Ad savedAd = adRepository.save(ad);

        return new AdDto(savedAd.getId(), savedAd.getTitle(), savedAd.getDescription(), savedAd.getUrl());
    }
}
