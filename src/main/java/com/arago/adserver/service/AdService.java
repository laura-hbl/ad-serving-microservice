package com.arago.adserver.service;

import com.arago.adserver.dto.AdDto;

public interface AdService {

    AdDto createAd(final AdDto addDTO);

    AdDto getAd(final String id);
}
