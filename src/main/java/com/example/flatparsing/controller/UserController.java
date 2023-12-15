package com.example.flatparsing.controller;

import com.example.flatparsing.service.ExternalApiHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.flatparsing.model.Flat;
import com.example.flatparsing.service.DBService ;

@RestController
public class UserController {
    private final DBService dbService;

    @Autowired
    public UserController(DBService dbService) {
        this.dbService = dbService;
    }

    @GetMapping
    public String sayHello(){
        return "Hello my dear friend";
    }

    @GetMapping("/db/all")
    public ResponseEntity<?> getFlatsFromDatabase() {
        List<Flat> flats = dbService.getAllFlats();
        StringBuilder sb = new StringBuilder();
        for (Flat f: flats) sb.append(f.toString());
        return new ResponseEntity<>(sb, HttpStatus.OK);
    }

    @GetMapping("/db/get")
    public ResponseEntity<String> syncFlatsFromExternalApi() {
        ExternalApiHandler externalApiHandler = new ExternalApiHandler();
        List<String> jsonFromApi = null;

        try {
            jsonFromApi = externalApiHandler.getJsonFromApi();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JsonDeserializer jsonDeserializer = new JsonDeserializer(dbService);

        try {
            for (String reader : jsonFromApi) {
                dbService.saveOrUpdateFlatsInDB(jsonDeserializer.getFlatFromJson(reader));
            }
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Data synchronized failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        dbService.setDeletedFlats();
        System.out.println("Data loaded in DB successful");
        return new ResponseEntity<>("Data synchronized successful", HttpStatus.OK);
    }
}
