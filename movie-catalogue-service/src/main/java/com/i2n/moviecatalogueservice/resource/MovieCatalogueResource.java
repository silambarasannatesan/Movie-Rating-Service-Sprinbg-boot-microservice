package com.i2n.moviecatalogueservice.resource;

import com.i2n.moviecatalogueservice.models.CatalogueItem;
import com.i2n.moviecatalogueservice.models.Movie;
import com.i2n.moviecatalogueservice.models.Ratings;
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
    public List<CatalogueItem> getCatalogueList(@PathVariable("userid") String userId) {

        List<Ratings> ratingsList = Arrays.asList(
                new Ratings("1234", 4),
                new Ratings("5678", 3)
        );

        return ratingsList.stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogueItem(movie.getName(), "Desc", rating.getRating());
        }).collect(Collectors.toList());

    }
}
