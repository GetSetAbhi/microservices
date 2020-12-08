package com.example.movie.catalog.resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.movie.catalog.model.CatalogItem;
import com.example.movie.catalog.model.Movie;
import com.example.movie.catalog.model.Rating;
import com.example.movie.catalog.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webclientBuilder;
	
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
	
	@RequestMapping("rest/{userId}")
	public List<CatalogItem> getResrCatalog(@PathVariable("userId") String userId) {
		
		
		
		UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
		
		List<Rating> ratings = userRating.getRatings();
		
		return ratings.stream().map(rating -> {
				Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
				return new CatalogItem(movie.getName(), "something", rating.getRating());
		}).collect(Collectors.toList());
	}
	
	@RequestMapping("webclient/{userId}")
	public List<CatalogItem> getCatalogAsync(@PathVariable("userId") String userId) {
		
		
		
		/*List<Rating> ratings = Arrays.asList(
				new Rating("123", 4),
				new Rating("124", 5)
		);*/
		
		// // communicates with ratings-data-service
		UserRating userRating = webclientBuilder.build().get()
				.uri("http://ratings-data-service/ratingsdata/user/" + userId)
				.retrieve()
				.bodyToMono(UserRating.class)
				.block();
		
		List<Rating> ratings = userRating.getRatings();
		
		return ratings.stream().map(rating -> {
				
			// communicates with movie-info-service
				Movie movie = webclientBuilder.build().get()
						.uri("http://movie-info-service/movies/" + rating.getMovieId())
						.retrieve()
						.bodyToMono(Movie.class)
						.block();
				
				return new CatalogItem(movie.getName(), "something", rating.getRating());
		}).collect(Collectors.toList());
	}

}
