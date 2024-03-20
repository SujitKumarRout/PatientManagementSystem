package com.sujit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sujit.enitity.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor, Integer>
{

}