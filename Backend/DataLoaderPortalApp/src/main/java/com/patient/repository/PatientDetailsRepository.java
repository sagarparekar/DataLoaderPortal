package com.patient.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.patient.model.PatientDetails;

@Repository
public interface PatientDetailsRepository extends JpaRepository<PatientDetails, Integer>{
	

	
	public PatientDetails findByPatientName(String patientName);
	
	
//	@Query(value="select p from PatientDetails p where p.patientName= :patientName")
//	public PatientDetails getPatientByName(@Param("patientName") String patientName);
}














