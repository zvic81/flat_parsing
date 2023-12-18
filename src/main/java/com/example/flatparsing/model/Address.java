package com.example.flatparsing.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
@Entity
@Table(name = "Address", schema = "public")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String formattedAddress;

    @OneToMany(mappedBy = "address")
    private List<Flat> flats;

    public Address(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
    public Address() {
    }


}
