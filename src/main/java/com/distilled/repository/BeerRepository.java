package com.distilled.repository;

import com.distilled.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {

    @Query(nativeQuery = true, value = "SELECT *  FROM beer WHERE name<>:beerName ORDER BY rand() LIMIT 1")
    Beer getRandomBeer(@Param("beerName") String beerName);

    List<Beer> getBeersByNameNot(String beerName);
}
