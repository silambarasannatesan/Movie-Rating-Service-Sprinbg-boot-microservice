package com.i2n.movieratingservice.models;

public class Ratings {
    private String ratingId;
    private String name;

    public Ratings(String ratingId, String name) {
        this.ratingId = ratingId;
        this.name = name;
    }

    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
