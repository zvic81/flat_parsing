package com.example.flatparsing.service;

import com.example.flatparsing.model.Address;
import com.example.flatparsing.model.Flat;
import com.example.flatparsing.repo.AddresRepo;
import com.example.flatparsing.repo.FlatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public char saveOrUpdateFlatsInDB(List<Flat> flats){
        for (Flat flat: flats) {
            Flat flatFromBD = flatRepo.findFirstByIdAvitoAndLattitudeAndLongitude(flat.getIdAvito(), flat.getLattitude(), flat.getLongitude()).orElse(null);
            if (flatFromBD == null) { //no same flat in DB
                flat.setUpdated((byte)1);
                flatRepo.save(flat);
                System.out.println("flat saved in DB: "+flat.getId());
            } else {
                if (flatFromBD.getCurrent_price() != flat.getCurrent_price()) flatFromBD.setCurrent_price(flat.getCurrent_price());
                if (!Objects.equals(flatFromBD.getDescription(), flat.getDescription())) flatFromBD.setDescription(flat.getDescription());
                if (!Objects.equals(flatFromBD.getTitle(), flat.getTitle())) flatFromBD.setTitle(flat.getTitle());
                flatFromBD.setUpdated((byte)1);
                flatRepo.save(flatFromBD);
                System.out.println("flat's updated "+flatFromBD.getId());
            }
        }
         return 0;
    }
    public char setDeletedFlats(){
        List<Flat> allFlats = flatRepo.findAll();
        for (Flat flat : allFlats) {
            if (flat.getUpdated() == 0) {
                flat.setDeleted((byte) 1);
                System.out.println("deleted flat: "+flat.getId());
            }
            flat.setUpdated((byte) 0);
        }
        flatRepo.saveAll(allFlats);
        System.out.println("set deleted finished");
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
