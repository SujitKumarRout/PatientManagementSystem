package com.sujit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sujit.dao.DiseaseRepository;
import com.sujit.enitity.Disease;

@Service
public class DiseaseService
{
    private DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseService(DiseaseRepository diseaseRepository)
    {
        this.diseaseRepository = diseaseRepository;
    }

    public List<Disease> getAllDiseases()
    {
        List<Disease> diseaseList = diseaseRepository.findAll();
        return diseaseList;
    }

    public void save(Disease disease)
    {
        diseaseRepository.save(disease);
    }

    public Disease findById(int id)
    {
        Disease newDisease =null;
        Optional<Disease> disease = diseaseRepository.findById(id);
        if(disease.isPresent())
        {
            newDisease = disease.get();
        }
        return newDisease;
    }


    public void deleteById(int id)
    {
        diseaseRepository.deleteById(id);
    }

}
