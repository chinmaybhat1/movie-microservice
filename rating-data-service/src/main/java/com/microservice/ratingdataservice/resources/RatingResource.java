package com.microservice.ratingdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ratingdataservice.models.Rating;
import com.microservice.ratingdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingdata")
public class RatingResource {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") long movieId) {

		return new Rating(movieId, 5);
	}

	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") long userId) {

		List<Rating> ratings = Arrays.asList(new Rating(100L, 4), new Rating(101L, 5));
		
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setUserRatings(ratings);

		return userRating;
	}
}
