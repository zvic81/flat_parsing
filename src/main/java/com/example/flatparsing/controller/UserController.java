package com.example.flatparsing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.flatparsing.model.Flat;
import com.example.flatparsing.service.DBService ;

@RestController
public class UserController {
    private final DBService dbService;

    public UserController(DBService dbService) {
        this.dbService = dbService;
    }

    @GetMapping
    public String sayHello(){
        return "Hello my dear friend";
    }

    @GetMapping("/db")
    public ResponseEntity<?> getFlatsFromDatabase() {
        List<Flat> flats = dbService.getAllFlats();
        StringBuilder sb = new StringBuilder();
        for (Flat f: flats) sb.append(f.toString());
        return new ResponseEntity<>(sb, HttpStatus.OK);
    }

}

