package com.example221.hospital.repositories;


import com.example221.hospital.models.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedecinRepositories extends JpaRepository<Medecin, Long> {
   /* @Query(" SELECT m FROM   JOIN medecin_specialite ON m.id=medecin_specialite.medecin_id JOIN specialite ON medecin_specialite.specialite_ids.id")
    public List<Medecin> findMedecinBySpecialite();*/
}
