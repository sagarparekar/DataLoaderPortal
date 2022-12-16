package com.patient.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class PatientDetails {
	
	@Id
	@GeneratedValue
	private int patientId;
	
	@Column(unique = true)
	@Pattern(regexp = "(^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$)")
	private String patientName;
	
//	@NotBlank(message = "Patient Address should not blank")
	private String patientAddress;
	
//	@Pattern(regexp = "^(((0)[0-9])|((1)[0-2]))(\\-)([0-2][0-9]|(3)[0-1])(\\-)\\d{4}$")
	@DateTimeFormat(pattern ="MM-dd-yyyy")
	@Past
	private Date patientDOB;
	
	
//	@NotBlank(message = "Patient Email should not blank")
	@Email
	private String patientEmail;
	
	
//	@NotBlank(message = "Contact Number should not blank")
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String patientContactNumber;
	

	@NotBlank(message = "Drug Id should not blank")
	@Pattern(regexp = "\\b\\d{5}\\-\\d{4}\\-\\d{2}\\b")
	private String patientDrugId;
	
	
	@NotBlank(message = "Drug Name should not blank")
	private String patientDrugName;
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public Date getPatientDOB() {
		return patientDOB;
	}

	public void setPatientDOB(Date patientDOB) {
		this.patientDOB = patientDOB;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getPatientContactNumber() {
		return patientContactNumber;
	}

	public void setPatientContactNumber(String patientContactNumber) {
		this.patientContactNumber = patientContactNumber;
	}

	public String getPatientDrugId() {
		return patientDrugId;
	}

	public void setPatientDrugId(String patientDrugId) {
		this.patientDrugId = patientDrugId;
	}

	public String getPatientDrugName() {
		return patientDrugName;
	}

	public void setPatientDrugName(String patientDrugName) {
		this.patientDrugName = patientDrugName;
	}

	@Override
	public String toString() {
		return "PatientDetails [patientId=" + patientId + ", patientName=" + patientName + ", patientAddress="
				+ patientAddress + ", patientDOB=" + patientDOB + ", patientEmail=" + patientEmail
				+ ", patientContactNumber=" + patientContactNumber + ", patientDrugId=" + patientDrugId
				+ ", patientDrugName=" + patientDrugName + ", status=" + status + "]";
	}

	
	

	
	

	


}
