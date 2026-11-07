package com.webservice.movie.week09_jpa_proj.entity;

import com.webservice.movie.week09_jpa_proj.dto.MovieRequest;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String director;

    @Column(nullable = false, length = 50)
    private String genre;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private Boolean watched;

    protected Movie() {}

    public Movie(MovieRequest request) {
        this.title = request.title();
        this.director = request.director();
        this.genre = request.genre();
        this.rating = request.rating();
        this.watched = request.watched();
    }

    public void update(MovieRequest request){
        this.title = request.title();
        this.director = request.director();
        this.genre = request.genre();
        this.rating = request.rating();
        this.watched = request.watched();
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDirector() {
        return director;
    }
    public String getGenre() {
        return genre;
    }
    public Integer getRating() {
        return rating;
    }
    public Boolean getWatched() {
        return watched;
    }

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
