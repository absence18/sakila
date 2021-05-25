package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CountryMapper;
import com.gd.sakila.vo.Country;
import com.gd.sakila.vo.PageParam;

@Service
@Transactional
public class CountryService {
	@Autowired 
	private CountryMapper countryMapper;	
	public Map<String, Object> getCountryList(int currentPage, int rowPerPage) {
		
		//컨트롤러에서 보내준 매개값을 가공
		int beginRow = (currentPage - 1)* rowPerPage;
		
		PageParam pageParam = new PageParam();
		pageParam.setBeginRow(beginRow);
		pageParam.setRowPerPage(rowPerPage);
		//dao호출
		List<Country> list = countryMapper.selectCountryList(pageParam);
		int total = countryMapper.selectCountryTotal();
		
		
		//dao의 반환값을 가공
		int lastPage = total / rowPerPage;
		if(total % rowPerPage != 0) {
			lastPage += 1;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("lastPage", lastPage);
		
		return map;
	}
}