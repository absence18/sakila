package com.gd.sakila.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data 
public class Page {
	private int rowPerPage;
	private int beginRow;
}