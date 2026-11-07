package com.webservice.movie.week09_jpa_proj.repository;

import com.webservice.movie.week09_jpa_proj.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Week 09: 기존 CRUD 메서드는 JpaRepository가 제공하므로 직접 작성할 필요가 없다!
    // Week 10: 확장! 간단한 Query Method를 여기 추가해보자.

    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByRatingBetween(Integer minRating, Integer maxRating);
    List<Movie> findByGenreOrderByRatingDesc(String genre);

}