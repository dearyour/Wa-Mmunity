package com.web.wam.model.service;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.web.wam.model.dto.wine.WineWishlistRequest;
import com.web.wam.model.repository.wine.WineWishlistRepository;

@ExtendWith(MockitoExtension.class)
public class WineServiceTest {

	@Mock
	private WineWishlistRepository wineWishlistRepository;

	@InjectMocks
	private WineService wineService = new WineServiceImpl();

	@Test
	@DisplayName("test saveWineWishlist function")
	void saveWineWishlist() {
		// given
		WineWishlistRequest wineWishlistRequest = new WineWishlistRequest(5, 123);
		Mockito.lenient().when(wineWishlistRepository.save(any())).thenReturn(wineWishlistRequest.toEntity());

		// when
		wineService.saveWishlist(wineWishlistRequest);

		// then
	}

}
