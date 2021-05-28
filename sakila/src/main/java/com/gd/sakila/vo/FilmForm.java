package com.gd.sakila.vo;

import lombok.Data;

@Data
public class FilmForm {
	
	private Category category;
	private Film film;
	private String lastUpdate;

}
