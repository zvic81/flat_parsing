package com.example.flatparsing.service;
import com.example.flatparsing.controller.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.PostConstruct;
import com.example.flatparsing.model.Flat;
import com.example.flatparsing.repo.FlatRepo;
import com.example.flatparsing.model.Address;
import com.example.flatparsing.repo.AddresRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import java.util.List;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class DataInitializer {
    private final FlatRepo flatRepo;
    private final AddresRepo addresRepo;

    @Autowired
    public DataInitializer(FlatRepo flatRepo, AddresRepo addresRepo) {
        this.flatRepo = flatRepo;
        this.addresRepo = addresRepo;
    }
    public void initializeData() throws IOException, URISyntaxException {
        JsonDeserializer jsonDeserializer = new JsonDeserializer(addresRepo);
        String fileContent = new String(Files.readAllBytes(Paths.get("src/main/resources/payload.json")));
//        List <Address>  addresses = jsonDeserializer.getAddressesFromJson(fileContent);
//        for (Address adr: addresses) {
//            String formattedAddress = adr.getFormattedAddress();
//            Address existingAddress = addresRepo.findFirstByFormattedAddress(formattedAddress);
//            if (existingAddress == null) {
//                addresRepo.save(adr); // save only unique address
//            }
//        }
        List <Flat> flats = jsonDeserializer.getFlatFromJson(fileContent);
//        for (Flat flat: flats) System.out.println(flat);
        flatRepo.saveAll(flats);
    }


    @PostConstruct
    public void initData(){
        try {
            initializeData();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
