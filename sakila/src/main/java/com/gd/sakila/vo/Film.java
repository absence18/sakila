package com.gd.sakila.vo;

import lombok.Data;

@Data
public class Film {
	
	private int filmId;
	private String title;
	private String description;
	private int releaseYear;
	private int languageId;
	private int originalLaguageId;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private int replacementCost;

	private String rating;

	private String[] specialFeatures = { "Trailers", "Commentaries", "Deleted Scenes", "Behind the Scenes" };
	private String lastUpdate;
}