package com.example221.hospital.service;


import com.example221.hospital.models.Specialite;
import com.example221.hospital.repositories.SpecialiteRepositorie;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
@Log
public class SpecialiteService {

    @Autowired
    private SpecialiteRepositorie specialiteRepositorie;

    public Specialite add_spacialite(Specialite specialite){
        try{
           specialiteRepositorie.save(specialite);
           return specialite;
        }catch (Exception e){
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public List<Specialite> get_All_Specialite(){
        return specialiteRepositorie.findAll();
    }

    public void delete_specialite(Long id){
        specialiteRepositorie.deleteById(id);
    }

    public Specialite find_specialite_by_id(Long id){

        return specialiteRepositorie.findById(id).orElse(null);
    }

}


