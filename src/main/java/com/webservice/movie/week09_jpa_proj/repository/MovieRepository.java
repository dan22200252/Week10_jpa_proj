package com.webservice.movie.week09_jpa_proj.repository;

import com.webservice.movie.week09_jpa_proj.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
Q3. JpaRepository<Entity, Long>에서 두 타입은 각각 무엇을 의미하는가?
A3. Entity 타입은 해당 Repository가 관리하는 Entity의 타입을 의미합니다.
    Long 타입은 해당 Repository가 관리하는 Entity의 기본키(PK)의 타입을 의미합니다.

Q4. JpaRepository에서 기본 제공하는 CRUD 메서드를 5개 이상 찾아 메서드명과 역할을 주석으로 작성하세요.
A4. save: Entity를 저장하거나 기존 Entity의 변경 사항을 반영합니다.
    findAll: 전체 Entity를 조회합니다.
    findById: 기본키를 사용하여 Entity를 조회합니다.
    deleteById: 기본키를 사용하여 Entity를 삭제합니다.
    existsById: 기본키를 사용하여 해당 Entity가 존재하는지 확인합니다.

Q5. findById()의 반환 타입이 Optional인 이유는 무엇인가?
A5. findById(id)로 조회했을 때 해당 Entity가 존재하지 않을 수 있기 때문입니다.
        이런 경우에 Optional은 조회 결과가 없을 때 null을 직접 반환하는 것이 아니라 Optional.empty()를 반환해서 null을 직접 다룰 때 발생 가능한 오류를 줄일 수 있도록 합니다.
 */

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Week 09: 기존 CRUD 메서드는 JpaRepository가 제공하므로 직접 작성할 필요가 없다!
    // Week 10: 확장! 간단한 Query Method를 여기 추가해보자.

    /*
    Q6. JpaRepository에서 기본 제공하는 메서드와 개발자가 직접 작성하는 Query Method의 차이는 무엇인가?
    A6. MovieRepository는 기본적인 공통 CRUD 메서드만 지원합니다. 기본 제공 메서드는 별도로 선언하지 않고도 상속받아 사용할 수 있습니다.
        메서드로 처리하기 어려운 복잡한 기능이 필요하다면 직접 Query Method를 작성합니다. 직접 작성한 메서드는 Spring Data JPA가 메서드 이름을 분석하고 해당 쿼리를 수행합니다.
     */
    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByRatingBetween(Integer minRating, Integer maxRating);
    List<Movie> findByGenreOrderByRatingDesc(String genre);

}