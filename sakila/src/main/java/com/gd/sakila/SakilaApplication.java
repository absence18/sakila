package com.gd.sakila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


@SpringBootApplication //스프링 부팅
@ServletComponentScan //스프링 애노테이션말고 서블릿 애노테이션도 스캔 가능하도록
@EnableScheduling //스케줄러를 찾아서 실행해줌
//@ComponentScan("com.gd.sakila") 기본적으로 @SpringBootApplication안에 들어있음
public class SakilaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SakilaApplication.class, args);
	}

}