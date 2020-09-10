package com.distilled.service;

import com.distilled.domain.Beer;
import com.distilled.repository.BeerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class BeerServiceTest {

    @Test
    public void testGetRandomBeerInApp() {
        List<Beer> beers = new ArrayList<>();
        beers.add(Beer.builder().name("beer2").build());
        beers.add(Beer.builder().name("beer3").build());

        BeerRepository beerRepository = Mockito.mock(BeerRepository.class);
        Mockito.when(beerRepository.getBeersByNameNot("beer1")).thenReturn(beers);
        BeerService beerService = new BeerService(beerRepository);

        Optional<Beer> beer = beerService.getRandomBeerInApp("beer1");
        Assert.assertTrue(beers.contains(beer.get()));
    }
}