package com.gd.sakila.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.StaffMapper;
import com.gd.sakila.vo.Page;
import com.gd.sakila.vo.Staff;
import com.gd.sakila.vo.StaffList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class StaffService {
	@Autowired StaffMapper staffMapper; // DI, @AutoWired 없으면 --> NullPointException 발생

	// login Service
	public Staff login(Staff staff) {
		log.debug(""+staff);
		return staffMapper.selectStaffByLogin(staff); // null or staff객체
	}

	// staffList Service
	public List<StaffList> getStaffList(int currentPage, int rowPerPage, String SearchWord) {
		Page page = new Page();
		page.setBeginRow((currentPage - 1)*rowPerPage); // 시작페이지
		page.setRowPerPage(rowPerPage);					// 현재페이지
		page.setSearchWord(SearchWord);					// 검색어

		return staffMapper.selectStaffList(page);
	}

}