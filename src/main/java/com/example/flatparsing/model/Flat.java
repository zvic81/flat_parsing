package com.example.flatparsing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name= "Flat", schema = "public")
public class Flat
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "id_avito")
    private long idAvito;
    @Column(name = "title")
    private String title;

    @Column(columnDefinition = "TEXT")
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

    @Column(name = "upd")
    private byte updated;
    @Column(name = "del")
    private byte deleted;

    public Flat() {
        // Оставьте этот конструктор пустым
    }
    public Flat(Address address, long idAvito, String title, String description) {
        this.address = address;
        this.idAvito = idAvito;
        this.title = title;
        this.description = description;
    }

    @PrePersist
    protected void onCreate() {
        creationDate = new Date();
    }

}
