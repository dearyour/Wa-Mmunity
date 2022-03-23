package com.web.wam.model.service;

import java.util.List;

import com.web.wam.model.dto.wine.WineResponse;
import com.web.wam.model.dto.wine.WineReviewPostRequest;
import com.web.wam.model.dto.wine.WineReviewPutRequest;
import com.web.wam.model.dto.wine.WineWishlistRequest;

public interface WineService {

	List<WineResponse> getAllWine();

	List<WineResponse> sortWine(int sortType);

	List<WineResponse> searchWineByKeyword(String keyword);

	WineResponse searchWindByWindId(int wineId);

	void createReview(WineReviewPostRequest wineReviewInfo);

	void updateReview(WineReviewPutRequest wineReviewInfo);

	void deleteReview(int wineReviewId);

	void saveWishlist(WineWishlistRequest wineWishListInfo);

	void deleteWishlist(int wishlistId);

	List<Integer> searchWishlistByMemberId(int memberId);

}
