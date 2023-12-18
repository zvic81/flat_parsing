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
    public ResponseEntity<List<Flat>> getFlatsFromDatabase() {
        List<Flat> flats = dbService.getAllFlats();
        return ResponseEntity.ok(flats);
    }

    @GetMapping("/db/get")
    public ResponseEntity<Response> syncFlatsFromExternalApi() {
        ExternalApiHandler externalApiHandler = new ExternalApiHandler();
        List<String> jsonFromApi;

        try {
            jsonFromApi = externalApiHandler.getJsonFromApi();
        } catch (InterruptedException e) {
            Response response = new Response("error", "An error occurred while processing the request");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        JsonDeserializer jsonDeserializer = new JsonDeserializer(dbService);

        try {
            for (String reader : jsonFromApi) {
                dbService.saveOrUpdateFlatsInDB(jsonDeserializer.getFlatFromJson(reader));
            }
        } catch (JsonProcessingException e) {
            Response response = new Response("error", "Data synchronized failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        dbService.setDeletedFlats();
        System.out.println("Data loaded in DB successful");
        Response response = new Response("ok", "Data synchronized successful");
        return ResponseEntity.ok().body(response);
    }
}
