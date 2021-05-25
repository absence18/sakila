package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Staff;
import com.gd.sakila.vo.StaffView;

@Mapper //마이파티스 에노테이션, 스프링 구동될때 xml에서 id가 같은걸 찾은다음 구현클래스 만들어줌
public interface StaffMapper {
	Staff selectStaffByLogin(Staff staff);
	List<StaffView> selectStaffViewList();
}