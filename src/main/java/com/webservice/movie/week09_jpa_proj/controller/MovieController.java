package com.webservice.movie.week09_jpa_proj.controller;

import com.webservice.movie.week09_jpa_proj.dto.MovieRequest;
import com.webservice.movie.week09_jpa_proj.dto.MovieResponse;
import com.webservice.movie.week09_jpa_proj.entity.Movie;
import com.webservice.movie.week09_jpa_proj.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MovieResponse> create(@Valid @RequestBody MovieRequest request) {
        MovieResponse created = service.create(request);
        return ResponseEntity
                .created(URI.create("/api/movies/" + created.id()))
                .body(created);
    }

    /*
    @GetMapping
    public List<MovieResponse> findAll() {
        return service.findAll();
    }
    */

    // Week 10: 확장 구현 findAll -> getMovies
    @GetMapping
    public List<MovieResponse> getMovies(@RequestParam(required = false) String title,
                                        @RequestParam(required = false) Integer minRating,
                                        @RequestParam(required = false) Integer maxRating,
                                        @RequestParam(required = false) String genre) {
        return service.getMovies(title, minRating, maxRating, genre);
    }

    @GetMapping("/{id}")
    public MovieResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @Valid @RequestBody MovieRequest request){
        return service.updateMovie(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
