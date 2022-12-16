package com.patient.validations;

import com.patient.model.PatientDetails;

/*
 To Handle All Patient Details validations
 */

public interface Validation {
	
	boolean isValid(PatientDetails patientDetails );

}
