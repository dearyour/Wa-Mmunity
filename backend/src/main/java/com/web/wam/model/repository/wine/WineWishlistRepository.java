package com.web.wam.model.repository.wine;

import com.web.wam.model.dto.wine.WineWishlistResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.wam.model.entity.WineWishlist;

import java.util.List;

public interface WineWishlistRepository extends JpaRepository<WineWishlist, Integer> {

    List<WineWishlist> findByMemberId(Integer memberId);
}
