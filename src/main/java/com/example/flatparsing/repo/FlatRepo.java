package com.example.flatparsing.repo;

import com.example.flatparsing.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;


public interface FlatRepo extends JpaRepository<Flat, Long> {
    Optional<Flat> findFirstByIdAvitoAndLattitudeAndLongitude(long idAvito, double lattitude, double longitude);
}

