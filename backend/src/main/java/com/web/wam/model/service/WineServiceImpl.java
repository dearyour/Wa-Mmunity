package com.web.wam.model.service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.web.wam.model.dto.wine.WineFilterRequest;
import com.web.wam.model.dto.wine.WineResponse;
import com.web.wam.model.dto.wine.WineReviewFlaskResponse;
import com.web.wam.model.dto.wine.WineReviewPostRequest;
import com.web.wam.model.dto.wine.WineReviewPutRequest;
import com.web.wam.model.dto.wine.WineReviewResponse;
import com.web.wam.model.dto.wine.WineSurveyRequest;
import com.web.wam.model.dto.wine.WineWishlistRequest;
import com.web.wam.model.dto.wine.WineWishlistResponse;
import com.web.wam.model.entity.Member;
import com.web.wam.model.entity.ReviewBaseRecomm;
import com.web.wam.model.entity.Wine;
import com.web.wam.model.entity.WineReview;
import com.web.wam.model.entity.WineSurvey;
import com.web.wam.model.entity.WineWishlist;
import com.web.wam.model.repository.MemberRepository;
import com.web.wam.model.repository.wine.ReviewBaseRecommRepository;
import com.web.wam.model.repository.wine.ReviewBaseRecommRepositorySupport;
import com.web.wam.model.repository.wine.WineRepository;
import com.web.wam.model.repository.wine.WineRepositorySupport;
import com.web.wam.model.repository.wine.WineReviewRepository;
import com.web.wam.model.repository.wine.WineReviewRepositorySupport;
import com.web.wam.model.repository.wine.WineSurveyRepository;
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
	WineReviewRepositorySupport wineReviewRepositorySupport;
	@Autowired
	WineWishlistRepository wineWishlistRepository;
	@Autowired
	WineWishlistRepositorySupport wineWishlistRepositorySupport;
	@Autowired
	WineSurveyRepository wineSurveyRepository;
	@Autowired
	ReviewBaseRecommRepository reviewBaseRecommRepository;
	@Autowired
	ReviewBaseRecommRepositorySupport reviewBaseRecommRepositorySupport;

	@Autowired
	MemberRepository memberRepository;

	private void setWineResponse(Wine wine, WineResponse wineResponse) {
		wineResponse.setWineId(wine.getWineId());
		wineResponse.setName(wine.getName());
		wineResponse.setImg(wine.getImg());
		wineResponse.setWinery(wine.getWinery());
		wineResponse.setWineStyle(wine.getWineStyle());
		wineResponse.setCountry(wine.getCountry());
		wineResponse.setRegion1(wine.getRegion1());
		wineResponse.setRegion2(wine.getRegion2());
		wineResponse.setRegion3(wine.getRegion3());
		wineResponse.setGrape1(wine.getGrape1());
		wineResponse.setGrape2(wine.getGrape2());
		wineResponse.setGrape3(wine.getGrape3());
		wineResponse.setCat1(wine.getCat1());
		wineResponse.setCat2(wine.getCat2());
		wineResponse.setPrice(wine.getPrice());
		wineResponse.setAlcoholContent(wine.getAlcoholContent());
		wineResponse.setAllergen1(wine.getAllergen1());
		wineResponse.setAllergen2(wine.getAllergen2());
		wineResponse.setAllergen3(wine.getAllergen3());
		wineResponse.setRatingAvg(wine.getRatingAvg());
		wineResponse.setRatingNum(wine.getRatingNum());
		wineResponse.setOaky(wine.getOaky());
		wineResponse.setEarthy(wine.getEarthy());
		wineResponse.setBlackFruit(wine.getBlackFruit());
		wineResponse.setRedFruit(wine.getRedFruit());
		wineResponse.setSpices(wine.getSpices());
		wineResponse.setFloral(wine.getFloral());
		wineResponse.setDriedFruit(wine.getDriedFruit());
		wineResponse.setAgeing(wine.getAgeing());
		wineResponse.setYeasty(wine.getYeasty());
		wineResponse.setVegetal(wine.getVegetal());
		wineResponse.setCitrus(wine.getCitrus());
		wineResponse.setTreeFruit(wine.getTreeFruit());
		wineResponse.setBold(wine.getBold());
		wineResponse.setTannic(wine.getTannic());
		wineResponse.setSweet(wine.getSweet());
		wineResponse.setAcidic(wine.getAcidic());
		wineResponse.setBeef(wine.getBeef());
		wineResponse.setLamb(wine.getLamb());
		wineResponse.setGame(wine.getGame());
		wineResponse.setPoultry(wine.getPoultry());
		wineResponse.setTropical(wine.getTropical());
		wineResponse.setPasta(wine.getPasta());
		wineResponse.setVeal(wine.getVeal());
		wineResponse.setCuredMeat(wine.getCuredMeat());
		wineResponse.setMatureAndHardCheese(wine.getMatureAndHardCheese());
		wine.setPork(wine.getPork());
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
	public WineResponse searchWineByWineId(int wineId) {
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
	public List<WineWishlistResponse> searchWishlistByMemberId(int memberId) {
		List<WineWishlist> wishlist = wineWishlistRepository.findByMemberId(memberId);
		List<WineWishlistResponse> responseList = new ArrayList<WineWishlistResponse>();
		for (WineWishlist wish : wishlist) {
			WineWishlistResponse res = new WineWishlistResponse();
			res.setMemberId(wish.getMemberId());
			res.setWineId(wish.getWineId());
			res.setId(wish.getId());
			responseList.add(res);
		}
		return responseList;
	}

	@Override
	public void saveWineSurvey(WineSurveyRequest wineSurveyRequest) {
		Optional<WineSurvey> wineSurvey = wineSurveyRepository.findByMemberId(wineSurveyRequest.getMemberId());
		if (wineSurvey.isEmpty()) {
			WineSurvey survey = new WineSurvey();
			survey.setMemberId(wineSurveyRequest.getMemberId());
			survey.setAmountOfAlcohol(wineSurveyRequest.getAmountOfAlcohol());
			survey.setSmellTaste1(wineSurveyRequest.getSmellTaste1());
			survey.setSmellTaste2(wineSurveyRequest.getSmellTaste2());
			survey.setSmellTaste3(wineSurveyRequest.getSmellTaste3());
			survey.setAcidicPreference(wineSurveyRequest.getAcidicPreference());
			survey.setSweetPreference(wineSurveyRequest.getSweetPreference());
			survey.setTannicPreference(wineSurveyRequest.getTannicPreference());
			survey.setBoldPreference(wineSurveyRequest.getBoldPreference());
			survey.setMinPrice(wineSurveyRequest.getMinPrice());
			survey.setMaxPrice(wineSurveyRequest.getMaxPrice());
			survey.setFood1(wineSurveyRequest.getFood1());
			survey.setFood2(wineSurveyRequest.getFood2());
			survey.setFood3(wineSurveyRequest.getFood3());
			wineSurveyRepository.save(survey);
		} else {
			wineSurvey.ifPresent(survey -> {
				survey.setAmountOfAlcohol(wineSurveyRequest.getAmountOfAlcohol());
				survey.setSmellTaste1(wineSurveyRequest.getSmellTaste1());
				survey.setSmellTaste2(wineSurveyRequest.getSmellTaste2());
				survey.setSmellTaste3(wineSurveyRequest.getSmellTaste3());
				survey.setAcidicPreference(wineSurveyRequest.getAcidicPreference());
				survey.setSweetPreference(wineSurveyRequest.getSweetPreference());
				survey.setTannicPreference(wineSurveyRequest.getTannicPreference());
				survey.setBoldPreference(wineSurveyRequest.getBoldPreference());
				survey.setMinPrice(wineSurveyRequest.getMinPrice());
				survey.setMaxPrice(wineSurveyRequest.getMaxPrice());
				survey.setFood1(wineSurveyRequest.getFood1());
				survey.setFood2(wineSurveyRequest.getFood2());
				survey.setFood3(wineSurveyRequest.getFood3());
				wineSurveyRepository.save(survey);
			});
		}
	}

	@Override
	public List<WineResponse> searchWineByFilter(WineFilterRequest filter) {
		List<WineResponse> wineList = new LinkedList<WineResponse>();
		List<Wine> wines = wineRepositorySupport.findByFilter(filter);
		for (Wine wine : wines) {
			WineResponse wineResponse = new WineResponse();
			setWineResponse(wine, wineResponse);
			wineList.add(wineResponse);
		}
		return wineList;
	}

	@Override
	public List<WineReviewResponse> searchReviewByMemberId(int memberId) {
		List<WineReviewResponse> reviewList = new LinkedList<WineReviewResponse>();
		List<WineReview> reviews = wineReviewRepository.findByMemberId(memberId);
		for (WineReview review : reviews) {
			WineReviewResponse wineReviewResponse = new WineReviewResponse();
			wineReviewResponse.setId(review.getId());
			wineReviewResponse.setMemberId(review.getMemberId());
			wineReviewResponse.setWineId(review.getWineId());
			wineReviewResponse.setRating(review.getRating());
			wineReviewResponse.setContent(review.getContent());
			wineReviewResponse.setRegtime(review.getRegtime());

			Optional<Member> member = memberRepository.findById(review.getMemberId());
			member.ifPresent(selectedMember -> {
				wineReviewResponse.setMemberName(selectedMember.getNickname());
				wineReviewResponse.setMemberEmail(selectedMember.getEmail());
			});

			reviewList.add(wineReviewResponse);
		}

		return reviewList;
	}

	@Override
	public double sumRatingByMemberId(int memberId) {
		List<WineReviewResponse> reviewList = searchReviewByMemberId(memberId);

		double totalRating = 0;

		for (WineReviewResponse review : reviewList) {
			totalRating += review.getRating();
		}

		return totalRating;
	}

	@Override
	public List<WineReviewResponse> searchReviewByWineId(int wineId) {
		List<WineReviewResponse> reviewList = new LinkedList<WineReviewResponse>();
		List<WineReview> reviews = wineReviewRepository.findByWineId(wineId);
		for (WineReview review : reviews) {
			WineReviewResponse wineReviewResponse = new WineReviewResponse();
			wineReviewResponse.setId(review.getId());
			wineReviewResponse.setMemberId(review.getMemberId());
			wineReviewResponse.setWineId(review.getWineId());
			wineReviewResponse.setRating(review.getRating());
			wineReviewResponse.setContent(review.getContent());
			wineReviewResponse.setRegtime(review.getRegtime());

			Optional<Member> member = memberRepository.findById(review.getMemberId());
			member.ifPresent(selectedMember -> {
				wineReviewResponse.setMemberName(selectedMember.getNickname());
				wineReviewResponse.setMemberEmail(selectedMember.getEmail());
			});

			reviewList.add(wineReviewResponse);
		}

		return reviewList;
	}

	@Override
	public List<WineResponse> recommendWineByWineId(int wineId) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		RestTemplate restTemplate = new RestTemplate();

		String wines = restTemplate.getForObject("http://j6a101.p.ssafy.io:5000/recomm/cb/" + wineId, String.class);

		StringTokenizer st = new StringTokenizer(wines.substring(1, wines.length() - 1), ", ");
		List<WineResponse> wineList = new LinkedList<WineResponse>();
		while (st.hasMoreTokens()) {
			int wine = Integer.parseInt(st.nextToken());
			WineResponse wineResponse = searchWineByWineId(wine);
			if (wineResponse.getName() != null) {
				wineList.add(wineResponse);
			}
		}

		return wineList;
	}

	@Override
	public void recommendWineByReview() {
		// review_base_recomm 테이블 안의 값 비우기
		reviewBaseRecommRepository.truncate();

		// user review 기반으로 예상 평점 계산
		List<WineReviewFlaskResponse> wineReviews = searchAllReviewForFlask();
		String reviews = "{ \"Results\" : [";
		for (WineReviewFlaskResponse review : wineReviews) {
			String s = "{ \"user\" : " + review.getUser() + ", \"wine\" : " + review.getWine() + ", \"rating\" : "
					+ review.getRating() + "},";

			reviews += s;
		}

		reviews = reviews.substring(0, reviews.length() - 1);
		reviews += "] }";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
				+ "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		HttpEntity<String> entity = new HttpEntity<String>(reviews, headers);

		RestTemplate restTemplate = new RestTemplate();

		String result = restTemplate.postForObject("http://j6a101.p.ssafy.io:5000/recomm/train-mf", entity,
				String.class);
		System.out.println(result.substring(11, result.length() - 1));

		JSONParser parser = new JSONParser();
		Object obj = null;

		try {
			obj = parser.parse(result.substring(11, result.length() - 1));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JSONArray jsonArr = (JSONArray) obj;

		if (jsonArr.size() > 0) {
			for (int i = 0; i < jsonArr.size(); i++) {
				JSONObject jsonObj = (JSONObject) jsonArr.get(i);

				// review_base_recomm 테이블에 값 저장하기
				ReviewBaseRecomm expect = new ReviewBaseRecomm();
				expect.setExpRating(Double.parseDouble(jsonObj.get("est_rating").toString()));
				expect.setMemberId(Integer.parseInt(jsonObj.get("user").toString()));
				expect.setWineId(Integer.parseInt(jsonObj.get("wine").toString()));
				reviewBaseRecommRepository.save(expect);
			}
		}

	}

	private List<WineReviewFlaskResponse> searchAllReviewForFlask() {
		List<WineReviewFlaskResponse> reviewList = new LinkedList<WineReviewFlaskResponse>();
		List<WineReview> reviews = wineReviewRepository.findAll();
		for (WineReview review : reviews) {
			WineReviewFlaskResponse wineReviewFlaskResponse = new WineReviewFlaskResponse();
			wineReviewFlaskResponse.setUser(review.getMemberId());
			wineReviewFlaskResponse.setWine(review.getWineId());
			wineReviewFlaskResponse.setRating(review.getRating());
			reviewList.add(wineReviewFlaskResponse);
		}

		return reviewList;
	}

	@Override
	public List<WineReviewResponse> searchAllReview() {
		List<WineReviewResponse> reviewList = new LinkedList<WineReviewResponse>();
		List<WineReview> reviews = wineReviewRepository.findAll();
		for (WineReview review : reviews) {
			WineReviewResponse wineReviewResponse = new WineReviewResponse();
			wineReviewResponse.setId(review.getId());
			wineReviewResponse.setMemberId(review.getMemberId());
			wineReviewResponse.setWineId(review.getWineId());
			wineReviewResponse.setRating(review.getRating());
			wineReviewResponse.setContent(review.getContent());
			wineReviewResponse.setRegtime(review.getRegtime());

			Optional<Member> member = memberRepository.findById(review.getMemberId());
			member.ifPresent(selectedMember -> {
				wineReviewResponse.setMemberName(selectedMember.getNickname());
				wineReviewResponse.setMemberEmail(selectedMember.getEmail());
			});

			reviewList.add(wineReviewResponse);
		}

		return reviewList;
	}

	@Override
	public List<WineReviewFlaskResponse> expectWineRateByMemberId(int memberId) {
		List<WineReviewFlaskResponse> wineList = new LinkedList<WineReviewFlaskResponse>();

		List<ReviewBaseRecomm> expects = reviewBaseRecommRepositorySupport.findByMemberId(memberId);
		for (ReviewBaseRecomm expect : expects) {
			WineReviewFlaskResponse wineReviewFlaskResponse = new WineReviewFlaskResponse();
			wineReviewFlaskResponse.setUser(expect.getMemberId());
			wineReviewFlaskResponse.setWine(expect.getWineId());
			wineReviewFlaskResponse.setRating(expect.getExpRating());
			wineList.add(wineReviewFlaskResponse);
		}
		return wineList;
	}

}
