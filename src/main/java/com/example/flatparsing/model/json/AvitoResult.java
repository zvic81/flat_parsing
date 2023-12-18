package com.example.flatparsing.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import java.util.Arrays;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AvitoResult {
    Item[] items;
    double lat;
    double lon;

    @Override
    public String toString() {
        return "AvitoResult{" +
                "items=" + Arrays.toString(items) +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
