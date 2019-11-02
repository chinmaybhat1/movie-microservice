package com.microservice.movieinfoservice.models;

public class Movie {

	private long movieId;
	private String name;
	private String desc;

	public Movie(long movieId, String name, String desc) {
		super();
		this.movieId = movieId;
		this.name = name;
		this.desc = desc;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
