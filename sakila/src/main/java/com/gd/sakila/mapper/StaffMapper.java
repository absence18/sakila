package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Page;
import com.gd.sakila.vo.Staff;
import com.gd.sakila.vo.StaffList;

/*
 * @Component, @Repository, @Service, @Controller --> Bean --> spring,getBean(클래스타입), @AutoWired <-- Dependcy Injection
 * @Mapper : mybatis의 애노테이션 --> @Repository의 역할 --> 맵퍼_인터페이스 --> 컴파일(?) 시 구현클래스 자동으로 생성
 * 
 */
@Mapper // mapper.xml을 찾아서 메서드명과 mapper의 id명이 같으면 합쳐서 메서드 오버라이딩을 한다
public interface StaffMapper {
	Staff selectStaffByLogin(Staff staff);
	List<StaffList> selectStaffList(Page page);
}