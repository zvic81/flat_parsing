package com.example.flatparsing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Flat
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private float price;

    public Flat() {
    }

    public Flat(String description, float price) {
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                "}; \n";
    }
}
