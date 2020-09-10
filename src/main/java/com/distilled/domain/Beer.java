package com.distilled.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;

    /**
     * Alcohol% by volume
     */
    private Double abv;

    /**
     * Brewery location of alcohol
     */
    private String breweryLocation;

    /**
     * The images are stored in base64 for ease.
     * Ideally they should be stored in an external storage like S3 served via CDN
     */
    private String imageBase64;
}
