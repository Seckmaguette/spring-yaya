package com.example221.hospital.controllers;



import com.example221.hospital.models.Service;
import com.example221.hospital.models.Specialite;
import com.example221.hospital.service.ServiceService;
import com.example221.hospital.service.SpecialiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping
public class SpecialiteController {

    @Autowired
    private SpecialiteService specialiteService;

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/specialite")
    public String viewSpecialite(Model model){
        List<Specialite> specialites = specialiteService.get_All_Specialite();
        model.addAttribute("specialite",specialites);
        return "specialite/list";
    }

    @GetMapping("/add-specialite")
    public String viewAddSpecialite(Model model){
        Specialite sp = new Specialite();
        List<Service> services = serviceService.listService();
        model.addAttribute("specialite",sp);
        model.addAttribute("service",services);
        return "specialite/addSpecialite";
    }

    @GetMapping("/specialite/edit/{id}")
    public String viewEditSpecialite(@PathVariable("id")Long id,Model model){
        Specialite sp = specialiteService.find_specialite_by_id(id);
        model.addAttribute("specialite",sp);
        List<Service> services = serviceService.listService();
        model.addAttribute("services",services);
        return "specialite/edit";

    }
    @GetMapping("/specialite/delete/{id}")
    public String deleteSpecialite(@PathVariable("id") Long id){
       specialiteService.delete_specialite(id);
       return "redirect:/specialite";
    }

    @PostMapping("/specialite/add")
    public String add_specialite(@ModelAttribute("specialite")Specialite sp, BindingResult bindingResult, Model model)
            throws IOException {
        if(sp == null){
            model.addAttribute("error","specialite incorrect");
            return "specialite/addSpecialite";
        }


        if(bindingResult.hasErrors()){
            return "specialite/addSpecialite";
        }
        boolean valide = true;
        if(sp.getLibelle()==null || sp.getLibelle().equals("")){
            model.addAttribute("error","libelle obligatoire");
            valide = false;
        }

        if(sp.getService() == null || sp.getService().getId()== 0){
            model.addAttribute("error","service obligatoire");
            valide = false;
        }
        if(valide){
            specialiteService.add_spacialite(sp);
            return "redirect:/specialite";
        }
        return "redirect:/add-specialite";
    }
    @PostMapping("/specialite/edit/{id}")
    public String edit_specialite(@PathVariable("id")Long id,Specialite sp,Model model){
        if(sp != null){
            sp.setId(id);
        }
        specialiteService.add_spacialite(sp);
        return "redirect:/specialite";

    }
}
