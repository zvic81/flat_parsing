package com.example.flatparsing.repo;

import com.example.flatparsing.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlatRepo extends JpaRepository<Flat, Long> {
}

