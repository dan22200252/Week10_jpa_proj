package com.webservice.movie.week09_jpa_proj.entity;

import com.webservice.movie.week09_jpa_proj.dto.MovieRequest;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
Q1. @Entity, @Id, @GeneratedValue는 각각 어떤 역할을 하는가?
A1. @Entity는 Movie 클래스를 데이터베이스와 연결해 JPA가 관리할 대상으로 인식하도록 합니다.
    @Id는 해당 필드를 데이터베이스 테이블의 기본키(PK)로 지정합니다.
    @GeneratedValue는 위의 기본키를 자동 생성하도록 합니다.

Q2. Entity 클래스와 데이터베이스 테이블은 어떻게 연결되는가?
A2. @Entity를 통해 관리 대상으로 지정된 Movie 클래스는 @Table(name = "movies") 설정을 통해서 데이터베이스의 movies 테이블과 연결됩니다.
    클래스 내 각각의 필드는 movies 테이블의 컬럼에 대응됩니다.
 */

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
