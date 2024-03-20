package com.sujit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sujit.enitity.Disease;


public interface DiseaseRepository extends JpaRepository<Disease, Integer>
{

}
