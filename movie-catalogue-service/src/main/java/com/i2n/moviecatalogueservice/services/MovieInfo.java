package com.i2n.moviecatalogueservice.services;

import com.i2n.moviecatalogueservice.models.CatalogueItem;
import com.i2n.moviecatalogueservice.models.Movie;
import com.i2n.movieratingservice.models.Ratings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogueItem")
    public CatalogueItem getCatalogueItem(Ratings rating) {
        Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/" + rating.getMovieId(), Movie.class);
        return new CatalogueItem(movie.getName(), "Desc", rating.getRating());
    }

    public CatalogueItem getFallbackCatalogueItem(Ratings rating) {
        return new CatalogueItem("Movie name not found", "", rating.getRating());
    }
}
