package com.arago.adserver.service;

import com.arago.adserver.dto.AdDto;
import com.arago.adserver.model.Ad;
import com.arago.adserver.repository.AdRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdServiceImplTest {

    @InjectMocks
    private AdServiceImpl adService;

    @Mock
    private AdRepository adRepository;

    @BeforeEach
    void setUp() {
        // for this mock return object passed as parameter
        when(adRepository.save(any())).thenAnswer(arg -> arg.getArguments()[0]);
    }

    @Test
    public void givenAnAdDto_whenCreateAd_thenReturnAdDtoWithId() {
        AdDto adDto = new AdDto("Ad1", "description", "http://ads");
        ArgumentCaptor<Ad> argumentCaptor = ArgumentCaptor.forClass(Ad.class);

        AdDto adCreated = adService.createAd(adDto);

        verify(adRepository).save(argumentCaptor.capture());
        assertThat(adCreated.getTitle()).isEqualTo(adDto.getTitle());
        assertThat(adCreated.getDescription()).isEqualTo(adDto.getDescription());
        assertThat(adCreated.getUrl()).isEqualTo(adDto.getUrl());
        assertThat(adCreated.getId()).isEqualTo(argumentCaptor.getValue().getId());
    }
}