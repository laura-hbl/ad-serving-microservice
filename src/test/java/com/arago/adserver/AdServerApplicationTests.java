package com.arago.adserver;

import com.arago.adserver.dto.AdDto;
import com.arago.adserver.service.AdService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdServerApplicationTests {

	@Autowired
	AdService adService;

	@Test
	@Disabled
	void integrationTestRedis() {
		// Created test to manually check object creation in redis server
		AdDto adDto = new AdDto("Ad1", "description", "http://ads");
		adService.createAd(adDto);
	}
}
