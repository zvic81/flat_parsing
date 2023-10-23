package com.example.flatparsing.service;

import com.example.flatparsing.model.Flat;
import com.example.flatparsing.repo.FlatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {
    private final FlatRepo flatRepo;

    @Autowired
    public DBService(FlatRepo flatRepo) {
        this.flatRepo = flatRepo;
    }
    public List<Flat> getAllFlats(){
        List<Flat> all = flatRepo.findAll();
        return all;
    }
}



/**
Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public AppUser getUserById(Long id){
        System.out.println("id getUserById=" + id);
        return userRepository.findById(id).orElse(null);
    }
    public List<AppUser> getAllUsers(){
        return userRepository.findAll();
    }
}
 */