package com.sujit.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sujit.enitity.Patient;
import com.sujit.service.DoctorService;
import com.sujit.service.PatientService;


@Controller
@RequestMapping("/patients")
public class PatientController
{
    private PatientService patientService;
    private DoctorService doctorService;
    private List<Patient> thePatients;

    public PatientController(PatientService patientService, DoctorService doctorService)
    {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel)
    {
        thePatients = patientService.getAllPatients();
        theModel.addAttribute("patients", thePatients);
        return "patients/list-patients";
    }

    @GetMapping("/addPatient")
    public String getPatientForm(Model model)
    {
        Patient patient = new Patient();
        model.addAttribute("doctorList",doctorService.getAllDoctors());
        model.addAttribute("patient",patient);
        return "patients/addPatient";
    }

    @PostMapping("/save")
    public String savePatient(@ModelAttribute("patient") Patient thePatient)
    {
        patientService.save(thePatient);
        return "redirect:/patients/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("patientId") int theID,Model model)
    {
        Patient patient = patientService.findById(theID);
        model.addAttribute("doctorList",doctorService.getAllDoctors());
        model.addAttribute("patient",patient);
        return "patients/addPatient";
    }

    @GetMapping("/delete")
    public String deletePatient(@RequestParam("patientId") int theID)
    {
        patientService.deleteById(theID);
        return "redirect:/patients/list";
    }
}