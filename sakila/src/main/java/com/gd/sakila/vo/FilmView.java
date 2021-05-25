package com.gd.sakila.vo;

import lombok.Data;

@Data
public class FilmView {
	private int FID;
	private String title;
	private String description;
	private String category;
	private double price;
	private int length;
	private String rating;
	private String actors;
}