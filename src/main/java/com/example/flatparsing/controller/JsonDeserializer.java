package com.example.flatparsing.controller;

import com.example.flatparsing.model.json.AvitoResult;
import com.example.flatparsing.model.json.Item;
import com.example.flatparsing.service.DBService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flatparsing.model.Address;
import com.example.flatparsing.model.Flat;

import static java.util.Collections.replaceAll;

@Service
public class JsonDeserializer {
    ObjectMapper objectMapper = new ObjectMapper();
    private final DBService dbService;

    @Autowired
    public JsonDeserializer(DBService dbService) {
        this.dbService = dbService;
    }

    public List<Flat> getFlatFromJson(String jsonBody) throws JsonProcessingException {
        List <Flat> flatList = new ArrayList<>();
        AvitoResult avitoResult = objectMapper.readValue(jsonBody, AvitoResult.class);
        for (Item item: avitoResult.getItems()) {
            Flat flat = new Flat();
            String formattedAddress = item.getGeo().getFormattedAddress();
            Address existingAddress = dbService.getAddressFromDB(formattedAddress);
            if (existingAddress == null) {
                existingAddress = new Address(formattedAddress);
                dbService.saveAddressToDB(existingAddress);   // save only unique address
            }
            flat.setAddress(existingAddress);
            String currentPrice = item.getPriceDetailed().getString();
            flat.setCurrent_price(Long.parseLong(currentPrice.replaceAll("[^\\d]", "")));
            flat.setId_avito(item.getId());
            flat.setDescription(item.getDescription());
            flat.setTitle(item.getTitle());
            flatList.add(flat);
        }
       return flatList;
    }
   }
