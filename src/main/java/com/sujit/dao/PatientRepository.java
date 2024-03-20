package com.sujit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sujit.enitity.Patient;


public interface PatientRepository extends JpaRepository<Patient, Integer>
{

}