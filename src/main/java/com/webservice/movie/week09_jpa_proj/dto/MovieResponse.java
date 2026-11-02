package com.webservice.movie.week09_jpa_proj.dto;

import com.webservice.movie.week09_jpa_proj.entity.Movie;

public record MovieResponse(
        Long id, String title, String director, String genre, Integer rating, Boolean watched
) {
    public static MovieResponse from(Movie movie) {
        return new MovieResponse(movie.getId(), movie.getTitle(), movie.getDirector(), movie.getGenre(), movie.getRating(), movie.getWatched());
    }
}
