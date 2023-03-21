package com.example221.hospital.controllers;



import com.example221.hospital.models.Medecin;
import com.example221.hospital.models.Service;
import com.example221.hospital.models.Specialite;
import com.example221.hospital.service.MedecinService;
import com.example221.hospital.service.ServiceService;
import com.example221.hospital.service.SpecialiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MedecinController {
    @Autowired
    private MedecinService medecinService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private SpecialiteService specialiteService;

    @GetMapping("/medecin")
    public String view_list_medecin(Model model){
      List<Medecin> medecins = medecinService.get_all_medecin();
      model.addAttribute("medecins",medecins);
      return "medecin/list";
    }

    @GetMapping("/medecin/delete/{id}")
    public String delete_medecin(@PathVariable("id") Long id){
        medecinService.delete_medecin(id);
        return "redirect:/medecin";
    }

    @GetMapping("/medecin/edit/{id}")
    public String view_edit_medecin(@PathVariable("id")Long id,Model model){
        Medecin m = medecinService.fy_medecin_by_id(id);
        model.addAttribute("medecin",m);
        List<Service> s = serviceService.listService();
        model.addAttribute("service",s);
        List<Specialite>sp = specialiteService.get_All_Specialite();
        model.addAttribute("specialites",sp);
        return "medecin/edit";
    }

    @GetMapping("/medecin/add")
    public String view_add_medecin(Model model){
        Medecin medecin = new Medecin();
        List<Service> services = serviceService.listService();
        List<Specialite> specialiteList = specialiteService.get_All_Specialite();
        model.addAttribute("medecin",medecin);
        model.addAttribute("service",services);
        model.addAttribute("specialites",specialiteList);
        return "medecin/addMedecin";
    }

    @PostMapping("/medecin/add-medecin")
    public String add_medecin(@ModelAttribute("medecin")Medecin medecin, BindingResult bindingResult,Model model) throws ParseException {

        if(medecin == null){
            model.addAttribute("error","specialite incorrect");
            return "medecin/addMedecin";
        }


        if(bindingResult.hasErrors()){
            return "medecin/addMedecin";
        }

        boolean valide =true;

        if(medecin.getNom() == null || medecin.getNom().equals("") ){
            model.addAttribute("error","nom obligatoire");
            valide = false;
        }

        if(medecin.getPrenom() == null || medecin.getPrenom().equals("") ){
            model.addAttribute("error","Prenom obligatoire");
            valide = false;
        }

        if(medecin.getSalaire() == 0 ){
            model.addAttribute("error","salaire obligatoire");
            valide = false;
        }

        if(medecin.getAdresse() == null || medecin.getAdresse().equals("") ){
            model.addAttribute("error","adresse obligatoire");
            valide = false;
        }

        if(medecin.getMatricule() == null || medecin.getMatricule().equals("")){
            model.addAttribute("error","matricule obligatoire");
            valide = false;
        }
        if(medecin.getDatenaiss() == null || medecin.getDatenaiss().equals("")){
            model.addAttribute("error","date obligatoire");
            valide = false;
        }
        if(medecin.getDatedembauche() == null || medecin.getDatedembauche().equals("")){
            model.addAttribute("error","date obligatoire");
            valide = false;
        }

        if(medecin.getService()==null || medecin.getService().getId()== 0){
            model.addAttribute("error","service obligatoire");
            valide = false;
        }
        if(valide){
            medecinService.add_medecin(medecin);
            return "redirect:/medecin";
        }
            return "redirect:/add-medecin";
    }

    @PostMapping("/medecin/edit/{id}")
    public  String edit_medecin(@PathVariable("id") Long id,Medecin medecin){
        if(medecin != null){
            medecin.setId(id);
        }

        medecinService.add_medecin(medecin);
        return  "redirect:/medecin";

    }
}
