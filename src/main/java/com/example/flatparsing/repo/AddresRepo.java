package com.example.flatparsing.repo;

import com.example.flatparsing.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddresRepo extends JpaRepository<Address, Long> {
    Address findFirstByFormattedAddress(String formattedAddress);
}
