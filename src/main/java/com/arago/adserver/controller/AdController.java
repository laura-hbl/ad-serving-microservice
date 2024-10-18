package com.arago.adserver.controller;

import com.arago.adserver.dto.AdDto;
import com.arago.adserver.service.AdService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;
    private final Bucket bucket;

    @Autowired
    public AdController(final AdService adService) {
        this.adService = adService;

        //rate limit of 5 requests per minute
        Refill refill = Refill.intervally(5, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(5, refill);
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @PostMapping
    public ResponseEntity<?> createAd(@Valid @RequestBody final AdDto adDto) {
        if (bucket.tryConsume(1)) {
            AdDto createdAd = adService.createAd(adDto);
            return new ResponseEntity<>(createdAd, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Rate limit exceeded. Try again later.");
        }
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
