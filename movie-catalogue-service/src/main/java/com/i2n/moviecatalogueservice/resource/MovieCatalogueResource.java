package com.i2n.moviecatalogueservice.resource;

import com.i2n.moviecatalogueservice.models.CatalogueItem;
import com.i2n.moviecatalogueservice.services.MovieInfo;
import com.i2n.moviecatalogueservice.services.UserRatingInfo;
import com.i2n.movieratingservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalogue")
public class MovieCatalogueResource {

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    WebClient.Builder webClientBuilder;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @GetMapping("/{userid}")
    public List<CatalogueItem> getCatalogue(@PathVariable("userid") String userId) {

        UserRating ratings = userRatingInfo.getUserRating(userId);
        return ratings.getUserRating().stream()
                .map(rating -> movieInfo.getCatalogueItem(rating))
                .collect(Collectors.toList());
    }

    public List<CatalogueItem> getFallbackCatalogue(@PathVariable("userid") String userId) {
        return Arrays.asList(new CatalogueItem("No Movie", "", 0));
    }
}
