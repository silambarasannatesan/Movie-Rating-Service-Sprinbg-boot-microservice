package com.i2n.movieratingservice.resource;

import com.i2n.movieratingservice.models.Ratings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingsResource {

    @GetMapping("/{movieId}")
    public Ratings getRatings(@PathVariable("movieId") String movieId) {
        return new Ratings(movieId, 4);
    }

}
