package com.webservice.movie.week09_jpa_proj.repository;

import com.webservice.movie.week09_jpa_proj.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Week 09: 기존 CRUD 메서드는 JpaRepository가 제공하므로 직접 작성할 필요가 없다!
}
