package com.example221.hospital.repositories;


import com.example221.hospital.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepositorie extends JpaRepository<Service, Long> {
}
