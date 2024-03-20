package com.sujit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sujit.enitity.Medicine;


public interface MedicineRepository extends JpaRepository<Medicine, Integer>
{

}