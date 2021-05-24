package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.FilmMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class FilmService {
	@Autowired FilmMapper filmMapper;
	// map <-- film, filmCount
	public Map<String, Object> getFilmOne(int filmId, int storeId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("filmId", filmId);
		paramMap.put("storeId", storeId);
		int filmCount = 0;
		paramMap.put("filmCount", filmCount);
		List<Integer> list = filmMapper.selectFilmInStock(paramMap);
		log.debug("★★★★★ filmCount :"+paramMap.get("filmCount"));
		log.debug("★★★★★ x :"+list);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		return returnMap;
	}
}