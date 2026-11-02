package com.webservice.movie.week09_jpa_proj.service;

import com.webservice.movie.week09_jpa_proj.dto.MovieRequest;
import com.webservice.movie.week09_jpa_proj.dto.MovieResponse;
import com.webservice.movie.week09_jpa_proj.entity.Movie;
import com.webservice.movie.week09_jpa_proj.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public MovieResponse create(MovieRequest request) {
        Movie saved = repository.save(new Movie(request));
        return MovieResponse.from(saved);
    }

    public List<MovieResponse> findAll() {
        return repository.findAll() // DB에서 모든 영화를 가져와 List를 반환
                .stream() //그 목록의 항목을 하나씩 처리할 수 있게 도움
                .map(MovieResponse::from) //각각의 Movie에 MovieResponse.from(movie)를 적용해 DTO로 변환: 각 응답의 영화 한 개당 하나의 응답 객체로!
                .toList(); //변환된 응답들을 List<MovieResponse>로 모음
                //즉, List<Movie>를 List<MovieResponse>로 변환하는 과정
    }

    public MovieResponse findById(Long id) {
        return MovieResponse.from(findEntity(id));
    }

    public MovieResponse update(Long id, MovieRequest request) {
        Movie movie = findEntity(id);
        movie.update(request);
        // Week 09: 9주차에는 아래와 같이 명시적으로 저장하는 방식을 채택했지만 10주차에서는 Jpa를 통해 자동으로 변경를 감지하고 반영하는 방식을 채택해볼 예정!
        return MovieResponse.from(repository.save(movie));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found: " + id);
        }
        repository.deleteById(id);
    }

    private Movie findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Movie not found: " + id));
    }
}
