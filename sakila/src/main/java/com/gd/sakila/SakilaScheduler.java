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
	
	// Scheduled 메서드는 void반환, 매개변수 0개
	
	// 0 20 11 : 11시20분 , 24 : 24일, * : 매달, * : 요일은 상관없다
	@Scheduled(cron = "0 17 11 24 * *")
	public void modifyCustomerActive() {
		customerService.modifyCustomerActiveByScheduler();
		log.debug("★★★★★ modifyCustomerActive 스케줄러 실행 완료!");
	}
}