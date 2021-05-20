package com.gd.sakila.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardForm {
	
	private Board board;
	
	private List<MultipartFile> boardfile; // 스프링에서는 파일 자체를 제공받는 방법이 있음!

}
