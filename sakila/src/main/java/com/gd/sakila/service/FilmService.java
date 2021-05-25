package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CategoryMapper;
import com.gd.sakila.mapper.FilmMapper;
import com.gd.sakila.mapper.RentalRateMapper;
import com.gd.sakila.mapper.RatingMapper;
import com.gd.sakila.vo.Film;
import com.gd.sakila.vo.FilmView;
import com.gd.sakila.vo.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class FilmService {
	@Autowired FilmMapper filmMapper;
	@Autowired CategoryMapper categoryMapper;
	@Autowired RatingMapper ratingMapper;
	@Autowired RentalRateMapper rentalRateMapper;
	
	public Map<String, Object> getFilmList(Map<String, Object> parmMap) {
		log.debug("★★★★★★★ FilmService.getFilmList 매개변수 currentPage : " + parmMap.get("currentPage"));
		log.debug("★★★★★★★ FilmService.getFilmList 매개변수 rowPerPage : " + parmMap.get("rowPerPage"));
		log.debug("★★★★★★★ FilmService.getFilmList 매개변수 searchWord : " + parmMap.get("searchWord"));
		log.debug("★★★★★★★ FilmService.getFilmList 매개변수 searchActor : " + parmMap.get("searchActor"));
		log.debug("★★★★★★★ FilmService.getFilmList 매개변수 category : " + parmMap.get("category"));
		log.debug("★★★★★★★ FilmService.getFilmList 매개변수 rating : " + parmMap.get("rating"));
		log.debug("★★★★★★★ FilmService.getFilmList 매개변수 rentalRate : " + parmMap.get("rentalRate"));
		
		int currentPage = (int)parmMap.get("currentPage");
		int rowPerPage = (int)parmMap.get("rowPerPage");
		String searchWord = (String)parmMap.get("searchWord");
		String searchActor = (String)parmMap.get("searchActor");
		String category = (String)parmMap.get("category");
		String rating = (String)parmMap.get("rating");
		String rentalRate = (String)parmMap.get("rentalRate");
		
		//카테고리리스트 가져오기 정렬기능 사용하기위해 카테고리mapper만들어서하기
		List<String> categoryList = categoryMapper.selectCategoryList();
		log.debug("★★★★★★★ FilmService.getFilmList categoryList :" + categoryList);
		//관람가등급리스트 가져오기 정렬기능 사용하기위해
		List<String> ratingList = ratingMapper.selectRatingList();
		log.debug("★★★★★★★ FilmService.getFilmList ratingList :" + ratingList);
		//가격리스트 가져오기 정렬기능 위해서
		List<String> rentalRateList = rentalRateMapper.selectRentalRateList();
		log.debug("★★★★★★★ FilmService.getFilmList rateList :" + rentalRateList);
		
		
		
		
		Page page = new Page();
		page.setBeginRow( (currentPage - 1 )*rowPerPage);
		page.setRowPerPage(rowPerPage);
		log.debug("★★★★★★★ FilmService.getFilmList page :" + page);
		
		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("page", page);
		tempMap.put("category", category);
		tempMap.put("searchWord", searchWord);
		tempMap.put("searchActor", searchActor);
		tempMap.put("rating", rating);
		tempMap.put("rentalRate", rentalRate);
		
		int filmTotal = filmMapper.selectFilmTotal(tempMap);
		int lastPage = (int)Math.ceil((double)filmTotal/ rowPerPage);
		log.debug("★★★★★★★ FilmService.getFilmList filmTotal :" + filmTotal);
		log.debug("★★★★★★★ FilmService.getFilmList lastPage :" + lastPage);
		
		List<FilmView> filmList = filmMapper.selectFilmList(tempMap);
		log.debug("★★★★★★★ FilmService.getFilmList filmList :" + filmList);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("categoryList", categoryList);
		returnMap.put("ratingList", ratingList);
		returnMap.put("rentalRateList", rentalRateList);
		returnMap.put("lastPage", lastPage);
		returnMap.put("filmList", filmList);
		returnMap.put("searchWord", searchWord);
		returnMap.put("searchActor", searchActor);
		
		return returnMap;
	}
	
	public Map<String, Object> getFilmOne(int filmId) {
		log.debug("★★★★★★★ FilmService.getFilmOne 매개변수  filmId:" + filmId);
		
		Map<String, Object> filmOne = filmMapper.selectFilmOne(filmId);
		log.debug("★★★★★★★ FilmService.getFilmOne filmOne :" + filmOne);
		
		Map<String, Object> store1Map = new HashMap<>();
		store1Map.put("filmId", filmId);
		store1Map.put("storeId", 1);
		
		int store1filmCount = 0;
		store1Map.put("filmCount", store1filmCount);
		List<Integer> store1Stock = filmMapper.selectFilmInStock(store1Map);
		log.debug("★★★★★★★ FilmService.getFilmOne store1filmCount :" + store1Map.get("filmCount"));
		log.debug("★★★★★★★★★★★★★★ FilmService.getFilmOne store1Stock :" + store1Stock);
		
		Map<String, Object> store2Map = new HashMap<>();
		store2Map.put("filmId", filmId);
		store2Map.put("storeId", 2);
		
		int store2filmCount = 0;
		store2Map.put("filmCount", store2filmCount);
		List<Integer> store2Stock = filmMapper.selectFilmInStock(store2Map);
		log.debug("★★★★★★★ FilmService.getFilmOne store2filmCount :" + store2Map.get("filmCount"));
		log.debug("★★★★★★★★★★★★★★ FilmService.getFilmOne store2Stock :" + store2Stock);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("store1Stock", store1Map.get("filmCount"));
		returnMap.put("store2Stock", store2Map.get("filmCount"));
		returnMap.put("filmOne", filmOne);
		return returnMap;
	}
	
}