package com.example221.hospital.service;



import com.example221.hospital.models.Medecin;
import com.example221.hospital.repositories.MedecinRepositories;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class MedecinService {

    @Autowired
    private MedecinRepositories medecinRepositories;

    public Medecin add_medecin(Medecin medecin){
        try {
            medecinRepositories.save(medecin);
            return medecin;
        }catch (Exception e){
            log.severe(e.getLocalizedMessage());
            throw  e;
        }
    }

   /* public List<Medecin> medecins_by_specialite(){
        return medecinRepositories.findMedecinBySpecialite();
    }*/

    public List<Medecin> get_all_medecin(){
        return medecinRepositories.findAll();
    }

    public Medecin fy_medecin_by_id(Long id){
        return medecinRepositories.findById(id).orElse(null);
    }

    public void delete_medecin (Long id){
        medecinRepositories.deleteById(id);
    }
}
