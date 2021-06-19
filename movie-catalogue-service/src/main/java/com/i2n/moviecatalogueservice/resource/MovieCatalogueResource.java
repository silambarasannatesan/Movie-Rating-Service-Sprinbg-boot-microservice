package com.i2n.moviecatalogueservice.resource;

import com.i2n.moviecatalogueservice.models.CatalogueItem;
import com.i2n.moviecatalogueservice.models.Movie;
import com.i2n.moviecatalogueservice.models.Ratings;
import com.i2n.movieratingservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalogue")
public class MovieCatalogueResource {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userid}")
    @HystrixCommand(fallbackMethod = "getFallbackCatalogue")
    public List<CatalogueItem> getCatalogue(@PathVariable("userid") String userId) {

        UserRating ratings = restTemplate.getForObject("http://MOVIE-RATINGS-SERVICE/ratings/users/" + userId, UserRating.class);

        return ratings.getUserRating().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogueItem(movie.getName(), "Desc", rating.getRating());
        })
                .collect(Collectors.toList());
    }

    public List<CatalogueItem> getFallbackCatalogue(@PathVariable("userid") String userId) {
        return Arrays.asList(new CatalogueItem("No Movie", "", 0));
    }
}
