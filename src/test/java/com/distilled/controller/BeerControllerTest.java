package com.distilled.controller;

import com.distilled.ApplicationTests;
import com.distilled.domain.Beer;
import com.distilled.dto.BeerDTO;
import com.distilled.dto.ResponseDTO;
import com.distilled.repository.BeerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BeerControllerTest extends ApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BeerRepository beerRepository;

    @Test
    public void testGetRandomBeerWhenEmpty() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/beer/random?currBeer=null"))
                .andExpect(status().isOk()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ResponseDTO<BeerDTO> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<ResponseDTO<BeerDTO>>() {
        });

        Assert.assertFalse(actual.isSuccess());
    }

    @Test
    public void testGetRandomBeerWhenAvailable() throws Exception {
        Beer beer = Beer.builder().id(1L).name("beer").build();
        beerRepository.save(beer);

        MvcResult result = this.mockMvc.perform(get("/api/beer/random?currBeer=null"))
                .andExpect(status().isOk()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ResponseDTO<BeerDTO> actualResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<ResponseDTO<BeerDTO>>() {
        });

        Assert.assertTrue(actualResponse.isSuccess());

        BeerDTO expectedBeer = BeerDTO.builder().name("beer").build();
        Assert.assertEquals(actualResponse.getData(), expectedBeer);
    }

    @Test
    public void testGetRandomBeerShouldReturnDifferentBeer() throws Exception {
        Beer beer1 = Beer.builder().id(1L).name("beer1").build();
        Beer beer2 = Beer.builder().id(1L).name("beer2").build();
        beerRepository.save(beer1);
        beerRepository.save(beer2);

        MvcResult result = this.mockMvc.perform(get("/api/beer/random?currBeer=beer1"))
                .andExpect(status().isOk()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        ResponseDTO<BeerDTO> actualResponse = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<ResponseDTO<BeerDTO>>() {
        });

        Assert.assertTrue(actualResponse.isSuccess());

        BeerDTO expectedBeer = BeerDTO.builder().name("beer2").build();
        Assert.assertEquals(actualResponse.getData(), expectedBeer);
    }

}