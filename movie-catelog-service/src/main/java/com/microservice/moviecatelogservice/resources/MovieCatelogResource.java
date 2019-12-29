package com.microservice.moviecatelogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.moviecatelogservice.models.CatelogItem;
import com.microservice.moviecatelogservice.models.Movie;
import com.microservice.moviecatelogservice.models.UserRating;

@RestController
@RequestMapping("/catelog")
public class MovieCatelogResource {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	public List<CatelogItem> getCatelog(@PathVariable("userId") String userId) {

		UserRating userRatings = restTemplate.getForObject("http://rating-data-service/ratingdata/users/" + userId,
				UserRating.class);

		return userRatings.getUserRatings().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

			return new CatelogItem(movie.getName(), movie.getDesc(), rating.getRating());
		}).collect(Collectors.toList());
	}

}
