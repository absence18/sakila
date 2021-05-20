package com.gd.sakila.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gd.sakila.mapper.StaffMapper;
import com.gd.sakila.vo.Staff;

@Service
public class StaffService {
	
	@Autowired StaffMapper staffMapper; // DI, @Autowired 없으면..? --> NullPointException 발쌩..
	public Staff login(Staff staff) {
		return staffMapper.selectStaffByLogin(staff); // null 또는 staff객체가 리턴됨..!
	}
	

}
