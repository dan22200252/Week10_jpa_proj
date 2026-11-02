# 1. 과제 소개
## 주제 및 주요 기능
### Spring Data JPA 기반 CRUD 구현 및 MariaDB 배포
영화 정보 관리 REST API 프로젝트입니다.

Spring Data JPA와 MariaDB를 활용하여 개발하였으며 영화 정보 CRUD 기능을 제공합니다.
- 영화 등록
- 영화 전체/단건 조회
- 영화 수정
- 영화 삭제

DataGrip에서 SQL과 저장 결과 확인하였고 최종적으로 Render 배포하였습니다.

---
## Entity 필드 구조
Movie 엔티티는 MariaDB의 movies 테이블과 연결됩니다.

| 구분 | 필드명 | Java 자료형 | MariaDB 자료형 | 제약 조건 및 설명 |
|---|---|---|----------------|---|
| 기본 키 | `id` | `Long` | `BIGINT`       | 기본 키, 자동 증가 |
| 영화 제목 | `title` | `String` | `VARCHAR(100)` | 필수 입력 |
| 감독 | `director` | `String` | `VARCHAR(100)` | 필수 입력 |
| 장르 | `genre` | `String` | `VARCHAR(50)`  | 필수 입력 |
| 평점 | `rating` | `Integer` | `INT`          | 필수 입력 |
| 시청 여부 | `watched` | `Boolean` | `BIT`          | 필수 입력 |

---
## 프로젝트 구조 및 개발환경
### 프로젝트 구조
    src/main/java/com/webservice/movie/week09_jpa_proj
    ├── controller
    │   └── MovieController.java
    ├── dto
    │   ├── MovieRequest.java
    │   └── MovieResponse.java
    ├── entity
    │   └── Movie.java
    ├── repository
    │   └── MovieRepository.java
    ├── service
    │   └── MovieService.java
    └── Week09JpaProjApplication.java

    src/main/resources
    └── application.properties

    root
    ├── build.gradle
    ├── Dockerfile
    └── README.md

### 개발환경
- Java 17
- Gradle
- Spring Boot 4.1.2
- Spring WebMVC, Spring Data JPA
- MariaDB JDBC Driver
- Spring Boot Validation
- IntelliJ IDEA, DataGrip, Postman

---
## GitHub Repository URL
- 과제 제출 Repository: https://github.com/2026-2-WebService/assign09-c01-22200252
- 개인 Repository(Render 배포 연결): https://github.com/dan22200252/Week09_jpa_proj

---
## Render Deployment URL
- Render 서비스 URL: https://week09-jpa-proj.onrender.com
- 영화 목록 API: https://week09-jpa-proj.onrender.com/api/movies


# 2. Database SQL Practice
## 기존 Table 조회 및 데이터 추가 결과
    SELECT * FROM book;
![sql-book-select.png](images/sql-book-select.png)

    INSERT INTO book (title, author, price, category)
    VALUES ('테스트 책 이름', '테스트 책 저자', 45000, '테스트');
![sql-book-insert.png](images/sql-book-insert.png)

---
## 새 Table 생성 SQL
### Table 생성
    CREATE TABLE movie_sql (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(100) NOT NULL,
        director VARCHAR(100),
        genre VARCHAR(50),
        rating DOUBLE
    );
![sql-movie-schema.png](images/sql-movie-schema.png)

---
## INSERT / UPDATE / DELETE / SELECT SQL과 실행 결과
### INSERT — 데이터 추가
    INSERT INTO movie_sql(title, director, genre, rating)
    VALUES ('Inception', 'Nolan', 'SF', 9.1), ('Interstellar', 'Nolan', 'SF', 9.3), ('Parasite', 'Bong', 'Drama', 9.0);
![sql-movie-insert.png](images/sql-movie-insert.png)

### UPDATE — 데이터 수정
    UPDATE movie_sql SET rating = 9.5 WHERE title = 'Interstellar';
![sql-movie-update.png](images/sql-movie-update.png)

### DELETE — 데이터 삭제
    DELETE FROM movie_sql WHERE title = 'Parasite';
![sql-movie-delete.png](images/sql-movie-delete.png)

### SELECT — 전체 및 일부 조회
    SELECT * FROM movie_sql;
![sql-movie-select-all.png](images/sql-movie-select-all.png)

    SELECT title, rating FROM movie_sql;
![sql-movie-select-columns.png](images/sql-movie-select-columns.png)

    SELECT * FROM movie_sql WHERE genre = 'SF';
![sql-movie-select-genre.png](images/sql-movie-select-genre.png)

---
## DataGrip 실행 화면
movie_sql 최종 결과 화면
![sql-movie-final-datagrip.png](images/sql-movie-final-datagrip.png)

# 3. Spring Data JPA 구현
## Entity 및 JpaRepository 설명
MovieRepository는 JpaRepository<Movie, Long>을 상속합니다.

이를 통해서 Movie Entity를 대상으로 별도의 함수를 새로 선언하지 않아도 기본적인 CRUD 메서드를 사용할 수 있습니다.
- 엔티티명: Movie
- MariaDB 테이블명: movies

### 필드
- id(Long)
- title(String)
- director(String)
- genre(String)
- rating(Integer)
- watched(Boolean)

---
## 사용한 Annotation의 역할
### Spring Boot / API Annotation
| 어노테이션 | 역할 |
|---|---|
| `@SpringBootApplication` | 메인 클래스에서 애플리케이션 설정과 컴포넌트 탐색을 시작 |
| `@RestController` | 컨트롤러의 반환값을 HTTP 응답 본문으로 반환 |
| `@RequestMapping("/api/movies")` | 컨트롤러의 기본 경로를 `/api/movies`로 지정 |
| `@PostMapping` | POST 요청을 처리하는 메서드에 사용. 영화 등록 API에 적용 |
| `@GetMapping` | GET 요청을 처리하는 메서드에 사용. 영화 목록 또는 단건 조회 API에 적용 |
| `@PutMapping` | PUT 요청을 처리하는 메서드에 사용. 기존 영화 정보 수정 API에 적용 |
| `@DeleteMapping` | DELETE 요청을 처리하는 메서드에 사용. 영화 삭제 API에 적용 |
| `@RequestBody` | 요청 본문의 JSON을 객체로 변환 |
| `@PathVariable` | URL의 `{id}` 값을 메서드 인자로 전달 |
| `@Service` | 클래스를 서비스 계층으로 표시 |

### JPA Annotation
| 어노테이션 | 역할                                                  |
|---|-------------------------------------------------------|
| `@Entity` | 클래스를 JPA가 관리하는 엔티티로 지정                 |
| `@Table(name = "movies")` | 엔티티가 연결될 데이터베이스 테이블을 `movies`로 지정 |
| `@Id` | 엔티티의 기본 키 필드를 지정                          |
| `@GeneratedValue(strategy = GenerationType.IDENTITY)` | 기본 키를 데이터베이스의 자동 증가 방식으로 생성      |
| `@Column(nullable = false, length = ...)` | 컬럼의 NULL 허용 여부와 문자열 길이 등 컬럼 속성 지정 |
'...' 에는 임의의 숫자가 들어갈 수 있습니다.

### Request 데이터 검증
| 어노테이션 | 역할 |
|---|---|
| `@Valid` | 요청 객체에 선언된 검증 규칙을 실행 |
| `@NotBlank` | 문자열이 null, 빈 문자열 또는 공백만 있는 값인지 검증 |
| `@Size(max = ...)` | 문자열의 최대 길이를 검증 |
| `@NotNull` | 값이 null이 아닌지 검증 |
| `@PositiveOrZero` | 숫자가 0 이상인지 검증 |
'...' 에는 임의의 숫자가 들어갈 수 있습니다.

---
## REST API Endpoint 목록 및 요청·응답 JSON
### REST API Endpoint 목록

| 메서드 | URL | 기능 | 테스트 결과 |
|---|---|---|---|
| `POST` | `/api/movies` | 영화 등록 | `201 Created` |
| `GET` | `/api/movies` | 영화 전체 조회 | `200 OK` |
| `GET` | `/api/movies/{id}` | ID로 영화 단건 조회 | `200 OK`, 없는 ID는 `404 Not Found` |
| `PUT` | `/api/movies/{id}` | 영화 정보 수정 | `200 OK` |
| `DELETE` | `/api/movies/{id}` | 영화 삭제 | `204 No Content` |

### 요청·응답 JSON

#### GET: 영화 전체 조회
요청 (`GET /api/movies`)

요청 본문은 없습니다.

응답 (`200 OK`)

    [
        {
            "id": 1,
            "title": "인터스텔라",
            "director": "크리스토퍼 놀란",
            "genre": "SF",
            "rating": 8,
            "watched": true
        },
        {
            "id": 2,
            "title": "인셉션",
            "director": "크리스토퍼 놀란",
            "genre": "SF",
            "rating": 9,
            "watched": true
        }
    ]
![render-api-get-all.png](images/render-api-get-all.png)

#### POST: 영화 등록 
요청(`POST /api/movies`)

    {
        "title": "Render CRUD 테스트",
        "director": "테스트 감독",
        "genre": "테스트",
        "rating": 7,
        "watched": false
    }

응답 (`201 Created`)

    {
      "id": 4,
      "title": "Render CRUD 테스트",
      "director": "테스트 감독",
      "genre": "테스트",
      "rating": 7,
      "watched": false
    }
![render-api-post-create.png](images/render-api-post-create.png)

#### GET: 영화 단건 조회

요청 (`GET /api/movies/4`)

요청 본문은 없습니다.

응답 (`200 OK`)

    {
        "id": 4,
        "title": "Render CRUD 테스트",
        "director": "테스트 감독",
        "genre": "테스트",
        "rating": 7,
        "watched": false
    }
![render-api-get-by-id.png](images/render-api-get-by-id.png)

#### PUT: 영화 수정
요청 (`PUT /api/movies/4`)

    {
        "title": "Render CRUD 테스트",
        "director": "테스트 감독",
        "genre": "테스트",
        "rating": 8,
        "watched": true
    }

응답 (`200 OK`)

    {
      "id": 4,
      "title": "Render CRUD 테스트",
      "director": "테스트 감독",
      "genre": "테스트",
      "rating": 8,
      "watched": true
    }
![render-api-put-update.png](images/render-api-put-update.png)

#### DELETE: 영화 삭제
DELETE 요청은 별다른 반환 정보 없이 상태 `204 No Content`를 반환합니다. 삭제한 ID를 다시 조회하면 `404 Not Found`가 반환됩니다.
![render-api-delete.png](images/render-api-delete.png)
![render-api-get-deleted-id-404.png](images/render-api-get-deleted-id-404.png)

#### 요청과 응답 사이 차이점
실제 응답에는 서버가 자동 생성한 id가 포함됩니다.

---
## JPA Entity와 MariaDB Table 비교
- JPA Entity: `Movie`
- MariaDB Table: `movies`

| 항목 | `Movie` 엔티티 | MariaDB `movies` 테이블 |
|---|---|---|
| 기본 키 | `id: Long` | `BIGINT`, 자동 증가 |
| 제목 | `title: String` | `VARCHAR(100)`, 필수 |
| 감독 | `director: String` | `VARCHAR(100)`, 필수 |
| 장르 | `genre: String` | `VARCHAR(50)`, 필수 |
| 평점 | `rating: Integer` | `INT`, 필수 |
| 시청 여부 | `watched: Boolean` | `BIT`, 필수 |


# 4. JPA와 SQL 비교
## save(), findAll(), findById(), deleteById() 사용 결과
### save()
![local-api-post-create.png](images/local-api-post-create.png)
![local-database-after-create.png](images/local-database-after-create.png)
### findAll()
![local-api-get-all.png](images/local-api-get-all.png)
### findById()
![local-api-get-by-id.png](images/local-api-get-by-id.png)
### deleteById()
![local-api-delete.png](images/local-api-delete.png)
![local-database-after-delete.png](images/local-database-after-delete.png)
![local-api-get-deleted-id-404.png](images/local-api-get-deleted-id-404.png)

---
## 실제 Hibernate SQL 확인
### INSERT
![hibernate-insert.png](images/hibernate-insert.png)
### UPDATE 및 SELECT
![hibernate-update-select.png](images/hibernate-update-select.png)
### DELETE
![hibernate-delete.png](images/hibernate-delete.png)
### ID 조건 SELECT
![hibernate-select-by-id.png](images/hibernate-select-by-id.png)

---
## SQL을 직접 작성하는 방식과 JPA 방식의 차이
SQL을 직접 작성하는 방식은 데이터베이스와 쿼리를 중심으로 데이터에 접근합니다.

JPA 방식은 자바 객체 중심으로 데이터에 접근합니다.

이러한 접근 방식의 차이를 중심으로 SQL을 직접 작성하는 방식은 개발자가 SQL을 직접 제어하므로 복잡한 대량 데이터나 통계 처리에 유리하지만, 코드가 길어지고 DB 변경에 취약합니다. 반면에 JPA 방식은 프레임워크가 SQL을 자동 생성해 주므로 생산성이 높고 객체지향적인 설계가 가능하지만, 초기 학습 비용이 들고 자동 쿼리를 최적화하는 이해도가 필요합니다.




# 5. 배포 및 환경 설정
## IntelliJ 및 Render 환경변수 설정 방법
- DB_URL
- DB_USERNAME
- DB_PASSWORD

애플리케이션이 데이터베이스에 연결할 때 필요한 환경변수를 IntelliJ와 Render에 설정했습니다. 

IntelliJ에서는 로컬 실행 설정에, Render에서는 서비스의 Environment 설정에 환경변수를 등록했습니다.
보안을 위해 README에는 환경 변수의 실제 값을 공개하지 않았습니다.
---
## Render 배포 과정
과제용 리포지터리와 같은 내용이 들어 있는 개인용 리포지터리를 Render에 연결하고 프로젝트 내의 Dockerfile을 통해 배포했습니다.

배포 전 프로젝트에서 사용할 DB와 관련된 적절한 환경변수를 미리 사이트에 입력한 후 배포하였습니다.

![render-deployment-live-logs.png](images/render-deployment-live-logs.png)
## 배포 URL의 CRUD 테스트 결과
### 전체 조회
![render-api-get-all.png](images/render-api-get-all.png)
### 등록
![render-api-post-create.png](images/render-api-post-create.png)
### 단건 조회
![render-api-get-by-id.png](images/render-api-get-by-id.png)
### 수정
![render-api-put-update.png](images/render-api-put-update.png)
### 삭제
![render-api-delete.png](images/render-api-delete.png)
### 삭제한 ID 재조회
![render-api-get-deleted-id-404.png](images/render-api-get-deleted-id-404.png)

## MariaDB 데이터 저장 확인
![mariadb-movies-table.png](images/mariadb-movies-table.png)

## 발생한 문제와 해결 방법
### 발생한 문제
Docker 방식으로 배포하려고 하는데 프로젝트 폴더에 Dockerfile을 만들지 않고 Render에 배포를 시도했을 때 애플리케이션을 실행할 배포 구성이 존재하지 않아 배포가 되지 않는 문제점을 발견했습니다.
### 해결 방법
관련 오류에 대해 알아본 결과 프로젝트 루트에 Dockerfile을 추가하고 Docker를 기반으로 배포해야 한다는 사실을 깨달았으며 이를 통해 해결했습니다.



# 6. Weekly Report
## Key Learning
1. Spring Data JPA의 JpaRepository를 활용하여 Movie CRUD 기능을 구현했습니다.
2. REST API의 HTTP 메서드를 활용하여 CRUD 요청을 처리하고 Postman을 활용하여 응답 상태와 JSON 결과를 확인했습니다.
3. Hibernate가 JPA 요청에 따라 실행하는 SQL을 확인했습니다.

## Problem & Solution
### Problem
Docker 방식으로 배포하려고 하는데 프로젝트 폴더에 Dockerfile을 만들지 않고 Render에 배포를 시도했을 때 애플리케이션을 실행할 배포 구성이 존재하지 않아 배포가 되지 않는 문제점을 발견했습니다.

### Solution
관련 오류에 대해 알아본 결과 프로젝트 루트에 Dockerfile을 추가하고 Docker를 기반으로 배포해야 한다는 사실을 깨달았으며 이를 통해 해결했습니다.

이를 통하여서 Docker와 Render를 사용하는 것, 그리고 로컬 환경에서 실행하는 것에 대한 차이를 명확하게 구분할 수 있게 되었습니다.

## Code Review
### 중요한 코드
    public interface MovieRepository extends JpaRepository<Movie, Long> { }
### 동작 설명
기존 save(), findAll(), findById(), deleteById()와 같은 CRUD 메서드는 JpaRepository가 제공하므로 직접 작성할 필요가 없습니다.


## AI Usage
### AI 활용 내용 및 직접 수정·검증한 부분
코드를 작성하는 도중 코드 이해 및 검토를 돕기위한 도구로 주로 사용했습니다.

코드 실행 중 발생하는 오류를 해결하는 데에 많은 도움을 받았으며 실습 스크린샷을 정리하는 데에도 활용하였습니다.

### 자신이 생각하는 AI사용률(%)
20%

## Reflection
### 추가로 공부하고 싶은 내용
이번 배포를 진행하면서 Docker가 어떤 방식으로 동작하는 것인지 조금 더 알아보고 싶다는 생각이 들었습니다.

또한 이번 과제를 진행하면서 10주차에 진행하게 될 자동 변경 감지 기능에 대해서도 더 깊은 호기심이 생겼습니다.
