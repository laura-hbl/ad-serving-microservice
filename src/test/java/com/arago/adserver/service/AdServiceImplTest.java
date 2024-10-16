package com.arago.adserver.service;

import com.arago.adserver.dto.AdDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdServiceImplTest {

    @InjectMocks
    private AdServiceImpl adService;

    @Test
    @Tag("CreateAd")
    @DisplayName("If, when createAd, then ")
    public void givenAnAdDto_whenCreateAd_thenReturnAdDtoWithId() {
        AdDto adDto = new AdDto("Ad1", "description", "http://ads");
        AdDto adCreated = adService.createAd(adDto);

        assertThat(adCreated.getTitle()).isEqualTo(adDto.getTitle());
        assertThat(adCreated.getDescription()).isEqualTo(adDto.getDescription());
        assertThat(adCreated.getUrl()).isEqualTo(adDto.getUrl());
        assertNotNull(adCreated.getId());
    }
}