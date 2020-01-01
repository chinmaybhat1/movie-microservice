package com.microservice.moviecatelogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.moviecatelogservice.models.CatelogItem;
import com.microservice.moviecatelogservice.models.UserRating;
import com.microservice.moviecatelogservice.services.MovieInfo;
import com.microservice.moviecatelogservice.services.UserRatingInfo;

@RestController
@RequestMapping("/catelog")
public class MovieCatelogResource {

	@Autowired
	MovieInfo movieInfo;

	@Autowired
	UserRatingInfo userRatingInfo;

	@RequestMapping("/{userId}")
	public List<CatelogItem> getCatelog(@PathVariable("userId") String userId) {

		UserRating userRatings = userRatingInfo.getUserRating(userId);

		return userRatings.getUserRatings().stream()
				.map(rating -> movieInfo.getCatelogItem(rating))
				.collect(Collectors.toList());
	}
}
