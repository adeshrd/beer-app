package com.distilled.service;

import com.distilled.domain.Beer;
import com.distilled.repository.BeerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BeerService {

    private final BeerRepository beerRepository;

    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    /**
     * Uses a simple rand() sql function to fetch random row
     *
     * @param beerName The current beer name loaded in UI. Used to fetch a different beer every time
     * @return Beer | null
     */
    public Beer getRandomBeer(String beerName) {
        return beerRepository.getRandomBeer(beerName);
    }

    /**
     * If we do not want to use a native sql query we can fetch all records
     * and then select a random record from it.
     * Not ideal if there are large number of records
     *
     * @param beerName The current beer name loaded in UI. Used to fetch a different beer every time
     * @return Beer | null
     */
    public Beer getRandomBeerInApp(String beerName) {
        List<Beer> beers = beerRepository.getBeersByNameNot(beerName);
        if (beers.isEmpty()) return  null; // We don't want a BadBound exception later

        Random rand = new Random();
        return beers.get(rand.nextInt(beers.size()));
    }
}
