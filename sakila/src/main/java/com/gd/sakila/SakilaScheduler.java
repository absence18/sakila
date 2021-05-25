package com.gd.sakila;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gd.sakila.service.CustomerService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class SakilaScheduler {
	@Autowired CustomerService customerService; 
	
	//스케줄러 메서드 void반환, 매개변수0개 (사용관련 https://cafe.naver.com/jjdev/9919 참고) 
	@Scheduled(cron="0 20 11 24 * *")
	public void modifyCustomerActive() {
		customerService.modifyCustomerActiveByscheduler();
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶SakilaScheduler.modifyCustomerActive 스케줄러 실행완료");
	}
}