package com.sujit.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sujit.enitity.Disease;
import com.sujit.enitity.Doctor;
import com.sujit.service.DiseaseService;
@Controller
@RequestMapping("/diseases")
public class DiseaseController
{
    private DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService)
    {
        this.diseaseService = diseaseService;
    }

    @GetMapping("/list")
    public String listDoctors(Model theModel)
    {
        theModel.addAttribute("diseaseList",diseaseService.getAllDiseases());
        return "disease/list-disease";
    }

    @GetMapping("/addDisease")
    public String getDoctorForm(Model model)
    {
        Disease disease = new Disease();
        model.addAttribute("disease",disease);
        return "disease/addDisease";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("disease") Disease theDoctor)
    {
        diseaseService.save(theDoctor);
        return "redirect:/diseases/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("diseaseId") int theID,Model model)
    {
        model.addAttribute("disease",diseaseService.findById(theID));
        return "disease/addDisease";
    }

    @Autowired
    @PersistenceContext
    private EntityManager em;
    @GetMapping("/delete")
    @Transactional
    public String deleteDoctor(@RequestParam("diseaseId") int theID)
    {
        Disease a = em.find(Disease.class, theID);
        for (Doctor b : a.getDoctors()) {
            if (b.getDisease() !=null)
            {
                em.remove(a);
            }
        }
        em.remove(a);
        return "redirect:/diseases/list";
    }
}









