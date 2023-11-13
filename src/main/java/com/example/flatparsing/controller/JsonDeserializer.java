package com.example.flatparsing.controller;

import com.example.flatparsing.model.json.AvitoResult;
import com.example.flatparsing.model.json.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flatparsing.repo.AddresRepo;
import com.example.flatparsing.model.Address;
import com.example.flatparsing.model.Flat;

@Service
public class JsonDeserializer {
    private final AddresRepo addresRepo;
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public JsonDeserializer(AddresRepo addresRepo) {
        this.addresRepo = addresRepo;
    }

    public List<Flat> getFlatFromJson(String jsonBody) throws IOException {
        List <Flat> flatList = new ArrayList<>();
        AvitoResult avitoResult = objectMapper.readValue(jsonBody, AvitoResult.class);
        for (Item item: avitoResult.getItems()) {
            Flat flat = new Flat();
            String formattedAddress = item.getGeo().getFormattedAddress();
            Address existingAddress = addresRepo.findFirstByFormattedAddress(formattedAddress);
            if (existingAddress == null) {
                existingAddress = new Address(formattedAddress);
                addresRepo.save(existingAddress); // save only unique address
            }
            flat.setAddress(existingAddress);
            flat.setCurrent_price(Long.parseLong(item.getPriceDetailed().getString()));
            flat.setId_avito(item.getId());
            flat.setDescription(item.getDescription());
            flat.setTitle(item.getTitle());
            flatList.add(flat);
        }
       return flatList;
    }
   }
