package com.example221.hospital.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
//@Data
//@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 50)
    private String libelle;
   /* @OneToMany(mappedBy = "service")
    List<Medecin> medecins ;*/
    @OneToMany(mappedBy = "service")
   List<Specialite> specialites;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Specialite> getSpecialites() {
        return specialites;
    }

    public void setSpecialites(List<Specialite> specialites) {
        this.specialites = specialites;
    }
}
