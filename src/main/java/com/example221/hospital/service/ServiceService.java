package com.example221.hospital.service;

import com.example221.hospital.models.Service;
import com.example221.hospital.repositories.ServiceRepositorie;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
@Log
public class ServiceService {

    @Autowired
    private ServiceRepositorie serviceRepositorie;

    public Service addService(Service service){
        try{
            serviceRepositorie.save(service);
            return service;
        }catch (Exception e){
            log.severe(e.getLocalizedMessage());
            throw e;
        }
    }

    public void delete(Long id){
       serviceRepositorie.deleteById(id);
    }

    public List<Service> listService(){
        return serviceRepositorie.findAll();
    }

    public Service findByLongId(Long id){
        return serviceRepositorie.findById(id).orElse(null);
    }
}

