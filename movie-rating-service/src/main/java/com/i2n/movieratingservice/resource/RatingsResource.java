package com.i2n.movieratingservice.resource;

import com.i2n.movieratingservice.models.Ratings;
import com.i2n.movieratingservice.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsResource {

    @GetMapping("/{movieId}")
    public Ratings getRatings(@PathVariable("movieId") String movieId) {
        return new Ratings(movieId, 4);
    }

    @GetMapping("users/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        List<Ratings> ratingsList = Arrays.asList(
                new Ratings("1234", 4),
                new Ratings("5678", 3)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratingsList);
        return userRating;
    }
}
