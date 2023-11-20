package com.example.flatparsing.service;

import com.example.flatparsing.model.Address;
import com.example.flatparsing.model.Flat;
import com.example.flatparsing.repo.AddresRepo;
import com.example.flatparsing.repo.FlatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {
    private final FlatRepo flatRepo;
    private final AddresRepo addresRepo;

    @Autowired
    public DBService(FlatRepo flatRepo, AddresRepo addresRepo) {
        this.flatRepo = flatRepo;
        this.addresRepo = addresRepo;
    }

    public List<Flat> getAllFlats(){
        List<Flat> all = flatRepo.findAll();
        return all;
    }

    public char saveFlatsToDB(List<Flat> flats){
        flatRepo.saveAll(flats);
        return 0;
    }
    public Address getAddressFromDB(String formattedAddress){
        return addresRepo.findFirstByFormattedAddress(formattedAddress);
    }

    public char saveAddressToDB(Address address){
        addresRepo.save(address);
        return 0;
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