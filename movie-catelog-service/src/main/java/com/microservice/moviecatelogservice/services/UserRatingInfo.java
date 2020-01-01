package com.microservice.moviecatelogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.moviecatelogservice.models.Rating;
import com.microservice.moviecatelogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class UserRatingInfo {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(
			fallbackMethod = "getFallbackUserRating", 
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000") 
			},
			threadPoolKey="userRatingInfo",
			threadPoolProperties= {
					@HystrixProperty(name="coreSize", value="20"),
					@HystrixProperty(name="maxQueueSize", value="10")
			}
	)
	public UserRating getUserRating(String userId) {
		return restTemplate.getForObject("http://rating-data-service/ratingdata/users/" + userId, UserRating.class);
	}

	public UserRating getFallbackUserRating(String userId) {

		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setUserRatings(Arrays.asList(new Rating(1000L, 10)));

		return userRating;
	}

}
