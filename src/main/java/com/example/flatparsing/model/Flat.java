package com.example.flatparsing.model;

import jakarta.persistence.*;
import java.util.Date;


@Entity
@Table(name= "Flat", schema = "public")
public class Flat
{
 public Flat(Address address, long id_avito, String title, String description, long current_price, Date creationDate, double lattitude, double longitude, boolean deleted) {
  this.address = address;
  this.id_avito = id_avito;
  this.title = title;
  this.description = description;
  this.current_price = current_price;
  this.lattitude = lattitude;
  this.longitude = longitude;
  this.deleted = deleted;
 }
 public Flat() {
  // Оставьте этот конструктор пустым
 }
 public Flat(Address address, long id_avito, String title, String description) {
        this.address = address;
        this.id_avito = id_avito;
        this.title = title;
        this.description = description;
    }

@PrePersist
protected void onCreate() {
    creationDate = new Date();
}

 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "id_avito")
    private long id_avito;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "current_price")
    private long current_price;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "lat")
    private double lattitude;
    @Column(name = "lon")
    private double longitude;
    @Column(name = "del")
    private boolean deleted;
}
