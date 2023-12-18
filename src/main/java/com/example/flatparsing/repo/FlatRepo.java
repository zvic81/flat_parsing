package com.example.flatparsing.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.flatparsing.model.Flat;


public interface FlatRepo extends JpaRepository<Flat, Long> {
    Optional<Flat> findFirstByIdAvitoAndLattitudeAndLongitude(long idAvito, double lattitude, double longitude);
}

