package com.microservice.moviecatelogservice.resources;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.moviecatelogservice.models.CatelogItem;

@RestController
@RequestMapping("/catelog")
public class MovieCatelogResource {

	@RequestMapping("/{userId}")
	public List<CatelogItem> getCatelog(@PathVariable("userId") String userId) {
		return Collections.singletonList(new CatelogItem("Joker", "Joker movie", 5));
	}

}
