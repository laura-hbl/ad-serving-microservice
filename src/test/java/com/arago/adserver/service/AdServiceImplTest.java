package com.arago.adserver.service;

import com.arago.adserver.dto.AdDto;
import com.arago.adserver.exception.ResourceNotFoundException;
import com.arago.adserver.model.Ad;
import com.arago.adserver.repository.AdRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdServiceImplTest {

    @InjectMocks
    private AdServiceImpl adService;

    @Mock
    private AdRepository adRepository;

/*    @BeforeEach
    void setUp() {
        // for this mock return object passed as parameter
        when(adRepository.save(any())).thenAnswer(arg -> arg.getArguments()[0]);
    }*/

    @Test
    public void givenAnAdDto_whenCreateAd_thenReturnAdDtoWithId() {
        AdDto adDto = new AdDto("Ad1", "my ad description", "http://ads.com");
        when(adRepository.save(any())).thenAnswer(arg -> arg.getArguments()[0]);
        ArgumentCaptor<Ad> argumentCaptor = ArgumentCaptor.forClass(Ad.class);

        AdDto result = adService.createAd(adDto);

        verify(adRepository).save(argumentCaptor.capture());
        assertEquals(result.getId(), result.getId());
        assertEquals(result.getTitle(), result.getTitle());
        assertEquals(result.getDescription(), result.getDescription());
        assertEquals(result.getUrl(), result.getUrl());
    }

    @Test
    public void givenAnAdId_whenGetAd_thenReturnExpectedAd() {
        Ad ad = new Ad("UUID", "Ad", "my ad description", "http://ads.com");
        when(adRepository.findById("UUID")).thenReturn(Optional.ofNullable(ad));

        AdDto result = adService.getAd("UUID");

        verify(adRepository).findById("UUID");
        assertNotNull(result);
        assertEquals(ad.getId(), result.getId());
        assertEquals(ad.getTitle(), result.getTitle());
        assertEquals(ad.getDescription(), result.getDescription());
        assertEquals(ad.getUrl(), result.getUrl());
    }

    @Test()
    public void givenAnUnregisteredId_whenGetAd_thenReturnEmpty() {
        when(adRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> adService.getAd(anyString()),
                "No ad registered with this id"
        );
    }
}