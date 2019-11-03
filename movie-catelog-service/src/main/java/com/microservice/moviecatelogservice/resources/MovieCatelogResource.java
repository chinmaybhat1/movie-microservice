package com.microservice.moviecatelogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.moviecatelogservice.models.CatelogItem;
import com.microservice.moviecatelogservice.models.Movie;
import com.microservice.moviecatelogservice.models.Rating;

@RestController
@RequestMapping("/catelog")
public class MovieCatelogResource {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	public List<CatelogItem> getCatelog(@PathVariable("userId") String userId) {

		List<Rating> ratings = Arrays.asList(new Rating(1000L, 4), new Rating(1001L, 5));

		return ratings.stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:7004/movies/" + rating.getMovieId(), Movie.class);
			return new CatelogItem(movie.getName(), movie.getDesc(), rating.getRating());
		}).collect(Collectors.toList());
	}

}
