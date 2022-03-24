package com.web.wam.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.web.wam.model.dto.wine.WineWishlistRequest;
import com.web.wam.model.entity.WineWishlist;
import com.web.wam.model.repository.wine.WineRepository;
import com.web.wam.model.repository.wine.WineWishlistRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WineRepositoryTest {

	@Autowired
	private WineRepository wineRepository;

	@Autowired
	private WineWishlistRepository wineWishlistRepository;

	@Test
	@DisplayName("Save wine wishlist at Database")
	void saveWineWishlist() {
		// given
		WineWishlist wineWishlist = new WineWishlistRequest(5, 123).toEntity();
		// when
		WineWishlist savedWineWishlist = wineWishlistRepository.save(wineWishlist);
		// then
		Assertions.assertThat(wineWishlist).isSameAs(savedWineWishlist);
		Assertions.assertThat(wineWishlist.getMemberId()).isSameAs(savedWineWishlist.getMemberId());
		Assertions.assertThat(wineWishlist.getWineId()).isSameAs(savedWineWishlist.getWineId());
	}
}
