# 과제 소개
# 프로젝트 설정
# 데이터베이스 연결
# SQL 실습
## ① Table 생성
    CREATE TABLE movie_sql (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(100) NOT NULL,
        director VARCHAR(100),
        genre VARCHAR(50),
        rating DOUBLE
    );
![01-movie-sql-schema.png](docs/screenshots/06-sql-practice/01-movie-sql-schema.png)

## ② INSERT — 데이터 추가
    INSERT INTO movie_sql(title, director, genre, rating)
    VALUES ('Inception', 'Nolan', 'SF', 9.1), ('Interstellar', 'Nolan', 'SF', 9.3), ('Parasite', 'Bong', 'Drama', 9.0);
![02-insert-results.png](docs/screenshots/06-sql-practice/02-insert-results.png)

## ③ UPDATE — 데이터 수정
    UPDATE movie_sql SET rating = 9.5 WHERE title = 'Interstellar';
![03-update-result.png](docs/screenshots/06-sql-practice/03-update-result.png)

## ④ DELETE — 데이터 삭제
    DELETE FROM movie_sql WHERE title = 'Parasite';
![04-delete-result.png](docs/screenshots/06-sql-practice/04-delete-result.png)

## ⑤ SELECT — 전체 및 일부 조회
    SELECT * FROM movie_sql;
![05-select-all-result.png](docs/screenshots/06-sql-practice/05-select-all-result.png)

    SELECT title, rating FROM movie_sql;
![06-select-title-rating-result.png](docs/screenshots/06-sql-practice/06-select-title-rating-result.png)

    SELECT * FROM movie_sql WHERE genre = 'SF';
![07-select-genre-result.png](docs/screenshots/06-sql-practice/07-select-genre-result.png)

# JPA 엔티티 및 테이블
# CRUD API
# Hibernate 실행 SQL
# Render 배포
# 문제 해결 및 느낀 점
# 주간 보고