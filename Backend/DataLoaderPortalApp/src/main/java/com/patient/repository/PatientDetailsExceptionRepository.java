package com.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.exception.PatientDetailsException;

public interface PatientDetailsExceptionRepository extends JpaRepository<PatientDetailsException, Integer>{

}
