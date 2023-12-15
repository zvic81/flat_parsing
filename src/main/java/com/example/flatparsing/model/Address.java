package com.example.flatparsing.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Address", schema = "public")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String formattedAddress;

    @OneToMany(mappedBy = "address")
    private List<Flat> flats;


    public Long getId() {
        return id;
    }
    public String getFormattedAddress() {
        return formattedAddress;
    }

    public Address(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
    public Address() {
    }
    public List<Flat> getFlats() {
        return flats;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", formatted_address='" + formattedAddress + '\'' +
                '}';
    }


}
