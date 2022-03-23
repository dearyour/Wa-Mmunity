package com.web.wam.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.web.wam.model.dto.wine.WineWishlistRequest;
import com.web.wam.model.service.WineService;

@WebMvcTest(WineController.class)
public class WineControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WineService wineService;

	@Test
	@DisplayName("saveWineWishlist success")
	void saveWineWishlistSuccess() throws Exception {
		// given
		final WineWishlistRequest wineWishlistRequest = initWineWishlistRequest();

		final ResultActions resuleActions = mockMvc
				.perform(MockMvcRequestBuilders.post("/wine/wishlist").contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(wineWishlistRequest)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").exists())
				.andExpect(jsonPath("$.object").exists()).andDo(print());

		verify(wineService).saveWishlist(wineWishlistRequest);
	}

	private WineWishlistRequest initWineWishlistRequest() {
		return new WineWishlistRequest(3, 153);
	}

}
