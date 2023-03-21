package com.example221.hospital.repositories;


import com.example221.hospital.models.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialiteRepositorie extends JpaRepository<Specialite, Long> {

    @Query("SELECT s FROM Specialite s WHERE  s.libelle = ?1")
    public Specialite fndByLibelle(String libelle);

}
