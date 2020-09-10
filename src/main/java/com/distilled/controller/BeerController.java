package com.distilled.controller;

import com.distilled.domain.Beer;
import com.distilled.dto.BeerDTO;
import com.distilled.dto.ResponseDTO;
import com.distilled.service.BeerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BeerController {

    private final BeerService beerService;

    BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    /**
     * GET API to fetch a random beer
     * We don't want rand() to fetch same beer twice so we pass currBeer to provide an exception
     * @return ResponseDTO of type Beer
     */
    @GetMapping(value = "/api/beer/random")
    public ResponseDTO<BeerDTO> getRandomBeer(@RequestParam("currBeer") String currBeer) {
        Beer beer = beerService.getRandomBeerInApp(currBeer);
        if (beer == null) {
            log.error("No beers found in the database in getRandomBeer()");
            return new ResponseDTO<BeerDTO>().withError("Sorry! No beers available");
        }

        BeerDTO beerDTO = BeerDTO.builder()
                .name(beer.getName())
                .description(beer.getDescription())
                .abv(beer.getAbv())
                .breweryLocation(beer.getBreweryLocation())
                .imageBase64(beer.getImageBase64())
                .build();

        log.debug(String.format("Fetched a random beer[id=%s] in getRandomBeer()", beer.getId()));
        return new ResponseDTO<BeerDTO>().withData(beerDTO);
    }
}
