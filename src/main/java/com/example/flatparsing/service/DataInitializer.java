package com.example.flatparsing.service;
import jakarta.annotation.PostConstruct;
import com.example.flatparsing.model.Flat;
import com.example.flatparsing.repo.FlatRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final FlatRepo flatRepo;

    @Autowired
    public DataInitializer(FlatRepo flatRepo) {
        this.flatRepo = flatRepo;
    }
    public void initializeData() {
        Flat flat1 = new Flat("flat1111", 1235);
        Flat flat2 = new Flat("flat22222222", 8787);
        flatRepo.save(flat1);
        flatRepo.save(flat2);
    }

//    @PostConstruct
//    public void initData(){
//        initializeData();
//    }

}
