package com.example.flatparsing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name= "Flat", schema = "public")
public class Flat
{
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
    @Column(name = "del")
    private boolean deleted;

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

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", address=" + address +
                ", id_avito=" + id_avito +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", current_price=" + current_price +
                ", creationDate=" + creationDate +
                ", lattitude=" + lattitude +
                ", longitude=" + longitude +
                ", deleted=" + deleted +
                '}';
    }

    @PrePersist
    protected void onCreate() {
        creationDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getId_avito() {
        return id_avito;
    }

    public void setId_avito(long id_avito) {
        this.id_avito = id_avito;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(long current_price) {
        this.current_price = current_price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
