package com.example221.hospital.controllers;


import com.example221.hospital.models.Service;
import com.example221.hospital.repositories.ServiceRepositorie;
import com.example221.hospital.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping
public class ServiceController {

    @Autowired
    private ServiceRepositorie serviceRepositorie;

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/")
    public String service(Model model){
        List<Service> services = serviceService.listService();
        model.addAttribute("services",services);
        return "service/list";
    }

    @GetMapping("/add-service")
    public String viewService(Model model){
        Service s = new Service();
        model.addAttribute("service",s);
        return "service/addService";
    }

    @GetMapping("/service/edit/{id}")
    public String viewEditService(@PathVariable("id")long id,Model model){
        Service s = serviceService.findByLongId(id);
        model.addAttribute("service",s);
        return "service/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable("id")long id){
      serviceService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/add-service")
    public String addService(@ModelAttribute("service")Service service, BindingResult bindingResult,Model model)
            throws IOException {
        if(service == null){
            model.addAttribute("error","service incorrect");
            return "service/addService";
        }


        if(bindingResult.hasErrors()){
            return "service/addService";
        }
        boolean valide = true;
        if(service.getLibelle()==null || service.getLibelle().equals("")){
            model.addAttribute("error","libelle obligatoire");
            valide = false;
        }
        if(valide){
            serviceService.addService(service);
            return "redirect:/";
        }
            return "redirect:/add-service";
    }

    @PostMapping("/service/edit/{id}")
    public String editService(@PathVariable("id")long id,Model model,Service service){

        if(service != null){
            service.setId(id);
        }
        serviceService.addService(service);
        return "redirect:/";
    }

}
