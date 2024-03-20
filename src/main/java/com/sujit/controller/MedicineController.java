package com.sujit.controller;

import java.util.List;

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

import com.sujit.enitity.Medicine;
import com.sujit.enitity.Patient;
import com.sujit.service.MedicineService;


@Controller
@RequestMapping("/medicines")
public class MedicineController
{
    private MedicineService medicineService;
    private List<Medicine> themedicines;

    public MedicineController(MedicineService medicineService)
    {
        this.medicineService = medicineService;
    }

    @GetMapping("/list")
    public String listmedicines(Model theModel)
    {
        themedicines = medicineService.getAllmedicines();
        theModel.addAttribute("medicines", themedicines);
        return "medicine/list-medicines";
    }

    @GetMapping("/addMedicine")
    public String getmedicineForm(Model model)
    {
        Medicine themedicine = new Medicine();
        model.addAttribute("medicine",themedicine);
        return "medicine/addMedicine";
    }

    @PostMapping("/save")
    public String savemedicine(@ModelAttribute("medicine") Medicine themedicine)
    {
        medicineService.save(themedicine);
        return "redirect:/medicines/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("medicineId") int theID,Model model)
    {
        Medicine medicine = medicineService.findById(theID);
        model.addAttribute("medicine",medicine);
        return "medicine/addMedicine";
    }

    @Autowired
    @PersistenceContext
    private EntityManager em;
    @GetMapping("/delete")
    @Transactional
    public String deletemedicine(@RequestParam("medicineId") int theID)
    {
        Medicine a = em.find(Medicine.class, theID);
        for (Patient p : a.getPatientList()) {
            if (p.getMedicineList().size() == 1) {
                em.remove(p);
            } else {
                p.getMedicineList().remove(a);
            }
        }
        em.remove(a);
        return "redirect:/medicines/list";
    }
}