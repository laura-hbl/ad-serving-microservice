package com.arago.adserver.controller;

import com.arago.adserver.dto.AdDto;
import com.arago.adserver.service.AdService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;

    @Autowired
    public AdController(final AdService adService) {
        this.adService = adService;
    }

    @PostMapping
    public ResponseEntity<AdDto> createAd(@Valid @RequestBody final AdDto adDto) {
        AdDto createdAd = adService.createAd(adDto);

        return new ResponseEntity<>(createdAd, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdDto> getAd(@PathVariable("id") final String adId) {

        AdDto ad = adService.getAd(adId);
        return new ResponseEntity<>(ad, HttpStatus.OK);
    }

    @GetMapping("/{id}/serve")
    public ResponseEntity<String> serveAd(@PathVariable("id") final String adId) {

        String adUrl = adService.serveAd(adId);
        return new ResponseEntity<>(adUrl, HttpStatus.OK);
    }

}
