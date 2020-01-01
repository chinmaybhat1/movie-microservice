package com.microservice.ratingdataservice.models;

import java.util.List;

public class UserRating {

	private long userId;
	private List<Rating> userRatings;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<Rating> getUserRatings() {
		return userRatings;
	}

	public void setUserRatings(List<Rating> userRatings) {
		this.userRatings = userRatings;
	}

}
