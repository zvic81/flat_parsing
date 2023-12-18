package com.example.flatparsing.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    Geo geo;
    PriceDetailed priceDetailed;
    Long id;
    String title;
    String description;
}
