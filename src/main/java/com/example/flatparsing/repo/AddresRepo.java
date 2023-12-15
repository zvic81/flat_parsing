package com.example.flatparsing.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.flatparsing.model.Address;


public interface AddresRepo extends JpaRepository<Address, Long> {
    Address findFirstByFormattedAddress(String formattedAddress);
}
