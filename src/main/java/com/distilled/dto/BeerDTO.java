package com.distilled.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDTO {
    private String name;
    private String description;
    private Double abv;
    private String breweryLocation;
    private String imageBase64;
}
