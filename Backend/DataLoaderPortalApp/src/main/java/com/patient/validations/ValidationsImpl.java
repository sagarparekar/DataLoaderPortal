package com.patient.validations;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.patient.model.PatientDetails;


/*
 To Handle All Patient Details validations
 */

@Component
public class ValidationsImpl implements Validation {

	Logger log = LoggerFactory.getLogger(ValidationsImpl.class);

	String emailRegex = "^(.+)@(.+)$";
	String patientNameRegex = "(^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$)";
	String drugIdRegex = "\\b\\d{5}\\-\\d{4}\\-\\d{2}\\b";
	String contactNumberRegex= "(^$|[0-9]{10})";
	String dobRegex="^(((0)[0-9])|((1)[0-2]))(\\-)([0-2][0-9]|(3)[0-1])(\\-)\\d{4}$";
	


	@Override
	public boolean isValid(PatientDetails patientDetails) {
	
		if(!isValidEmail(patientDetails.getPatientEmail()) ) {
			
			return false;
		}
		
		if(!isValidName(patientDetails.getPatientName())) {
			return false;
		}
		
		if(!isValidDrugId(patientDetails.getPatientDrugId())){
			return false;
		}
		
		if(!isValidContactNumber(patientDetails.getPatientContactNumber())) {
			return false;
		}
		
		if(!isValidDOB(patientDetails.getPatientDOB())) {
			return false;
		}
		
		if(patientDetails.getPatientAddress()==null && patientDetails.getPatientDrugName()==null) {
			log.info("Address or Drug Name is null");
			return false;
		}
		
		return true;
	}

//	Email Validation
	public boolean isValidEmail(String email) {
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			log.error("Email Pattern is Invaild " + email);
		}
		return matcher.matches();
	}
//patient name validation
	public boolean isValidName(String name) {
		if(name==null) {
			return false;
		}
		Pattern pattern = Pattern.compile(patientNameRegex);
		Matcher matcher = pattern.matcher(name);
		if (!matcher.matches()) {
			log.error("Patient Name Pattern is Invaild " + name);
		}
		return matcher.matches();
	}
	//Drug Id validations
	public boolean isValidDrugId(String drugId) {
		Pattern pattern = Pattern.compile(drugIdRegex);
		Matcher matcher = pattern.matcher(drugId);
		if (!matcher.matches()) {
			log.error("Drug Id Pattern is Invaild " + drugId);
		}
		return matcher.matches();
	}
	
	public boolean isValidContactNumber(String contactNumber) {
		Pattern pattern = Pattern.compile(contactNumberRegex);
		Matcher matcher = pattern.matcher(contactNumber);
		if (!matcher.matches()) {
			log.error("Contact Number Pattern is Invaild " +contactNumber);
		}
		return matcher.matches();
	}
	public boolean iaValidDateFormat(String dateFormat) {
		Pattern pattern = Pattern.compile(dobRegex);
		Matcher matcher = pattern.matcher(dateFormat);
		return matcher.matches();

	}
	
	public static boolean isDatePast(final String date, final String dateFormat) {
		LocalDate localDate = LocalDate.now(ZoneId.systemDefault());

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
		LocalDate inputDate = LocalDate.parse(date, dtf);

		return inputDate.isBefore(localDate);
	}
	
	
	
	
	public boolean isValidDOB(Date dob)  {
		if(dob==null) {
			return false;
		}
		Pattern pattern = Pattern.compile(dobRegex);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");  
		Matcher matcher = pattern.matcher(dateFormat.format(dob));
		log.info("Checking Date format : "+dateFormat.format(dob));
		if (!matcher.matches()) {
			log.error("Date of Birth Pattern is Invaild " +dob);
		}
		return matcher.matches() && isDatePast(dateFormat.format(dob),"MM-dd-yyyy");
	}
//To Manage exception in database
	public String isValidException(PatientDetails patientDetails) {
		
		if(!isValidName(patientDetails.getPatientName())) {
			return "Patient Name is Invalid";
		}
		
		if(!isValidEmail(patientDetails.getPatientEmail()) ) {
			
			return "Email is Invalid";
		}
		
		
		if(!isValidDrugId(patientDetails.getPatientDrugId())){
			return "Drug Id is Invalid";
		}
		
		if(!isValidContactNumber(patientDetails.getPatientContactNumber())) {
			return "Contact Number is Invalid";
		}
		
		if(!isValidDOB(patientDetails.getPatientDOB())) {
			return "Date of Birth is Invalid";
		}
		
		if(patientDetails.getPatientAddress()==null && patientDetails.getPatientDrugName()==null) {
			log.info("Address or Drug Name is null");
			return "Patient Address or Drug Name is Invalid";
		}
		
		return "";
	}

}
