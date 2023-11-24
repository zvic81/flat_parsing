package com.example.flatparsing.service;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExternalApiHandler {
    //        67.53199529502717   33.32436711542188 67.58337256719041 33.526927540226566 ---area covering the entire city of Apatity
    private static final double LAT_BOTTOM = 67.53199529502717;
    private static final double LON_LEFT = 33.32436711542188;
    private static final double LAT_TOP = 67.58337256719041;
    private static final double LON_RIGHT = 33.526927540226566;
    private static final double MAP_STEP_LAT = (LAT_TOP - LAT_BOTTOM)/3;
    private static final double MAP_STEP_LON = (LON_RIGHT - LON_LEFT)/3;

    public  List<String> getJsonFromApi() throws InterruptedException {
        List<String> resultString = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            double latBottomCurr = LAT_BOTTOM + MAP_STEP_LAT * x;
            double latTopCurr = LAT_BOTTOM + MAP_STEP_LAT + MAP_STEP_LAT * x;
            for (int y = 0; y < 3; y++) {
                double lonLeftCurr = LON_LEFT + MAP_STEP_LON * y;
                double lonRightCurr = LON_LEFT + MAP_STEP_LON + MAP_STEP_LON * y;
                Thread.sleep(1000);
                System.out.println("request for: "+latBottomCurr+"*"+lonLeftCurr+"*"+latTopCurr+"*"+lonRightCurr);
                Mono<String> response = WebClient.builder()
                        .baseUrl("https://www.avito.ru")
                        .build()
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/js/1/map/items")
                                .queryParam("categoryId", 24)
                                .queryParam("locationId", 640020)
                                .queryParam("correctorMode", 0)
                                .queryParam("page", 1)
                                .queryParam("params[201]", 1059)
                                .queryParam("params[499]", 5254)
                                .queryParam("verticalCategoryId", 1)
                                .queryParam("rootCategoryId", 4)
                                .queryParam("localPriority", 1)
                                .queryParam("disableByTitle", true)
                                .queryParam("subscription[visible]", true)
                                .queryParam("subscription[isShowSavedTooltip],false")
                                .queryParam("subscription[isErrorSaved]", false)
                                .queryParam("subscription[isAuthenticated]", true)
                                .queryParam("searchArea[latBottom]", latBottomCurr)
                                .queryParam("searchArea[lonLeft]", lonLeftCurr)
                                .queryParam("searchArea[latTop]", latTopCurr)
                                .queryParam("searchArea[lonRight]", lonRightCurr)
                                .queryParam("viewPort[width]", 1420)
                                .queryParam("viewPort[height]", 814)
                                .queryParam("limit", 10)
                                .queryParam("countAndItemsOnly", 1)
                                .build())
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(String.class);
                String append = ", \"lat\":" + (latBottomCurr+latTopCurr)/2 + ", \"lon\":" + (lonLeftCurr+lonRightCurr)/2 + "}";
                String ss = response.block();
                int length = ss.length();
                if (length>0) {
                    resultString.add(ss.substring(0, length - 1) + append);
                }
            }
        }
        return resultString;

    }
}
