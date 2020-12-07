package com.example.movie.catalog.resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.movie.catalog.model.CatalogItem;
import com.example.movie.catalog.model.Movie;
import com.example.movie.catalog.model.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		
		List<Rating> ratings = Arrays.asList(
				new Rating("123", 4),
				new Rating("124", 5)
		);
		
		return ratings.stream().map(rating -> {
				Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
				return new CatalogItem(movie.getName(), "something", rating.getRating());
		}).collect(Collectors.toList());
	}

}
