package com.gd.sakila.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Staff;

/*
 * @Component, @Repository, @Service, @Controller --> Bean --> 사용방법 : 1. spring.getBean(클래스타입), 2. @AutoWired.. <-- Dependency Injection
 * 
 * @Mapper는 Bean으로 만들어지긴 하지만.. mybatis의 에노테이션임.. 역할은 @Repository와 비슷함.. --> 맵퍼 + 인터페이스 --> 컴파일(?) 시 구현클래스 자동생성..
 * 
 */


@Mapper // mapper.xml을 찾아서 메서드명(selectStaffByLogin)과 mapper의 id명이 같으면 합쳐서 메서드를 구현클래스 생성시 overriding 한다..~!
public interface StaffMapper {
	
	Staff selectStaffByLogin(Staff staff);

}
