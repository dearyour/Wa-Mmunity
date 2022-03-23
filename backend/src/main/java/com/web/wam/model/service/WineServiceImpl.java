package com.web.wam.model.service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.wam.model.dto.wine.WineResponse;
import com.web.wam.model.dto.wine.WineReviewPostRequest;
import com.web.wam.model.dto.wine.WineReviewPutRequest;
import com.web.wam.model.dto.wine.WineWishlistRequest;
import com.web.wam.model.entity.Wine;
import com.web.wam.model.entity.WineReview;
import com.web.wam.model.entity.WineWishlist;
import com.web.wam.model.repository.wine.WineRepository;
import com.web.wam.model.repository.wine.WineRepositorySupport;
import com.web.wam.model.repository.wine.WineReviewRepository;
import com.web.wam.model.repository.wine.WineWishlistRepository;
import com.web.wam.model.repository.wine.WineWishlistRepositorySupport;

@Service("wineService")
public class WineServiceImpl implements WineService {

	@Autowired
	WineRepository wineRepository;
	@Autowired
	WineRepositorySupport wineRepositorySupport;
	@Autowired
	WineReviewRepository wineReviewRepository;
	@Autowired
	WineWishlistRepository wineWishlistRepository;
	@Autowired
	WineWishlistRepositorySupport wineWishlistRepositorySupport;

	private void setWineResponse(Wine wine, WineResponse wineResponse) {
		wineResponse.setWineId(wine.getWineId());
		wineResponse.setName(wine.getName());
		wineResponse.setPrice(wine.getPrice());
		wineResponse.setWinery(wine.getWinery());
		wineResponse.setGrapes(wine.getGrapes());
		wineResponse.setCountry(wine.getCountry());
		wineResponse.setRegions(wine.getRegions());
		wineResponse.setAllergens(wine.getAllergens());
		wineResponse.setCat1(wine.getCat1());
		wineResponse.setCat2(wine.getCat2());
		wineResponse.setRatingAvg(wine.getRatingAvg());
		wineResponse.setRatingNum(wine.getRatingNum());
		wineResponse.setTaste(wine.getTaste());
		wineResponse.setAcidic(wine.getAcidic());
		wineResponse.setAlcoholContent(wine.getAlcoholContent());
		wineResponse.setBold(wine.getBold());
		wineResponse.setTannic(wine.getTannic());
		wineResponse.setSweet(wine.getSweet());
		wineResponse.setStyle(wine.getStyle());
		wineResponse.setFoodParings(wine.getFoodParings());
	}

	@Override
	public List<WineResponse> getAllWine() {
		List<WineResponse> wineList = new LinkedList<WineResponse>();
		List<Wine> wines = wineRepository.findAll();
		for (Wine wine : wines) {
			WineResponse wineResponse = new WineResponse();
			setWineResponse(wine, wineResponse);
			wineList.add(wineResponse);
		}
		return wineList;
	}

	@Override
	public List<WineResponse> sortWine(int sortType) {
		/**
		 * 1 : 인기순 정렬 2 : 낮은 가격순 정렬 3 : 높은 가격순 정렬 4 : 리뷰 많은순 정렬
		 **/

		List<WineResponse> wineList = new LinkedList<WineResponse>();
		List<Wine> wines = new LinkedList<Wine>();
		switch (sortType) {
		case 1:
			wines = wineRepositorySupport.sortByRatingAvg();
			break;

		case 2:
			wines = wineRepositorySupport.sortByLowPrice();
			break;

		case 3:
			wines = wineRepositorySupport.sortByHighPrice();
			break;

		case 4:
			wines = wineRepositorySupport.sortByRatingNum();
			break;
		}

		for (Wine wine : wines) {
			WineResponse wineResponse = new WineResponse();
			setWineResponse(wine, wineResponse);
			wineList.add(wineResponse);
		}

		return wineList;
	}

	@Override
	public List<WineResponse> searchWineByKeyword(String keyword) {
		List<WineResponse> wineList = new LinkedList<WineResponse>();
		List<Wine> wines = wineRepositorySupport.searchWineByKeyword(keyword);

		for (Wine wine : wines) {
			WineResponse wineResponse = new WineResponse();
			setWineResponse(wine, wineResponse);
			wineList.add(wineResponse);
		}

		return wineList;
	}

	@Override
	public WineResponse searchWindByWindId(int wineId) {
		WineResponse wineResponse = new WineResponse();
		Optional<Wine> wine = wineRepository.findById(wineId);
		wine.ifPresent(selectWine -> {
			setWineResponse(selectWine, wineResponse);
		});
		return wineResponse;
	}

	@Override
	public void createReview(WineReviewPostRequest wineReviewInfo) {
		WineReview wineReview = new WineReview();
		wineReview.setWineId(wineReviewInfo.getWineId());
		wineReview.setMemberId(wineReviewInfo.getMemberId());
		wineReview.setRating(wineReviewInfo.getRating());
		wineReview.setContent(wineReviewInfo.getContent());
		wineReview.setRegtime(LocalDateTime.now());
		wineReviewRepository.save(wineReview);
		wineRepositorySupport.setRatingAvg(wineReview.getWineId(), wineReview.getRating());
		wineRepositorySupport.updateRatingCnt(wineReviewInfo.getWineId(), 1);
	}

	@Override
	public void updateReview(WineReviewPutRequest wineReviewInfo) {
		Optional<WineReview> review = wineReviewRepository.findById(wineReviewInfo.getWineReviewId());
		review.ifPresent(selectReview -> {
			selectReview.setRating(wineReviewInfo.getRating());
			selectReview.setContent(wineReviewInfo.getContent());
			selectReview.setRegtime(LocalDateTime.now());
			wineReviewRepository.save(selectReview);
			wineRepositorySupport.setRatingAvg(selectReview.getWineId(), wineReviewInfo.getRating());
		});
	}

	@Override
	public void deleteReview(int wineReviewId) {
		Optional<WineReview> review = wineReviewRepository.findById(wineReviewId);
		review.ifPresent(selectReview -> {
			wineReviewRepository.delete(selectReview);
			wineRepositorySupport.setRatingAvg(selectReview.getWineId(), selectReview.getRating());
			wineRepositorySupport.updateRatingCnt(selectReview.getWineId(), -1);
		});
	}

	@Override
	public void saveWishlist(WineWishlistRequest wineWishListInfo) {
		WineWishlist wishlist = new WineWishlist();
		wishlist.setMemberId(wineWishListInfo.getMemberId());
		wishlist.setWineId(wineWishListInfo.getWineId());
		wineWishlistRepository.save(wishlist);
	}

	@Override
	public void deleteWishlist(int wishlistId) {
		Optional<WineWishlist> wishlist = wineWishlistRepository.findById(wishlistId);
		wishlist.ifPresent(selectWishlist -> {
			wineWishlistRepository.delete(selectWishlist);
		});
	}

	@Override
	public List<Integer> searchWishlistByMemberId(int memberId) {
		List<Integer> wishlist = wineWishlistRepositorySupport.searchWishlistByMemberId(memberId);
		return wishlist;
	}

}
