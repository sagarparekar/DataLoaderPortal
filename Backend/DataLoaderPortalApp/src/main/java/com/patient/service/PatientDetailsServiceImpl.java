package com.patient.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.patient.exception.PatientDetailsException;
import com.patient.model.PatientDetails;
import com.patient.repository.PatientDetailsExceptionRepository;
import com.patient.repository.PatientDetailsRepository;
import com.patient.validations.ValidationsImpl;

@Service
public class PatientDetailsServiceImpl implements PatientDetailsService {
	@Autowired
	PatientDetailsRepository patientDetailsRepository;

	@Autowired
	ValidationsImpl validations;

	@Autowired
	PatientDetailsExceptionRepository patientDetailsExceptionRepository;

	Logger log = LoggerFactory.getLogger(PatientDetailsServiceImpl.class);

	@Override
	public List<PatientDetailsException> savePatientDetails(MultipartFile file) {
		List<PatientDetailsException> exceptions = new ArrayList<>();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
			while (rowIterator.hasNext()) {

				PatientDetails patient = new PatientDetails();
				PatientDetailsException patientDetailsException = new PatientDetailsException();
				Row row = rowIterator.next();
				int cid = 0;
				boolean isPatientNameExit = false;
				Iterator<Cell> cellIterator = row.cellIterator();
				DataFormatter dataformatter = new DataFormatter();

				if (row.getRowNum() != 0 && isCellEmpty(row)) {

					while (cellIterator.hasNext()) {

						Cell cell = cellIterator.next();

						switch (cid) {
						case 0:
							if (Objects.isNull(patientDetailsRepository.findByPatientName(cell.getStringCellValue()))) {
								patient.setPatientName(cell.getStringCellValue());
							} else {
								isPatientNameExit = true;
								patientDetailsException.setPatientName(cell.getStringCellValue());
								patientDetailsException.setExceptionMessage("Patient name is already exist");
								exceptions.add(patientDetailsException);
								patientDetailsException.setStatus("Failed");
								patientDetailsExceptionRepository.save(patientDetailsException);
							}
							break;
						case 1:
							patient.setPatientAddress(cell.getStringCellValue());
							break;
						case 2:
							if (validations.iaValidDateFormat(dataformatter.formatCellValue(cell))) {
								Date patientDOB = formatter.parse(String.valueOf(dataformatter.formatCellValue(cell)));
								patient.setPatientDOB(patientDOB);
							} else {
								patientDetailsException.setPatientName(patient.getPatientName());
								patientDetailsException.setExceptionMessage("Date format is Invalid");
								exceptions.add(patientDetailsException);
								patientDetailsExceptionRepository.save(patientDetailsException);
							}

							break;
						case 3:
							patient.setPatientEmail(cell.getStringCellValue());
							break;
						case 4:
							patient.setPatientContactNumber(dataformatter.formatCellValue(cell));
							break;
						case 5:
							patient.setPatientDrugId(cell.getStringCellValue());
							break;
						case 6:
							patient.setPatientDrugName(cell.getStringCellValue());
							break;

						default:
							break;
						}

						cid++;
					}
					log.info("Row Added " + row.getRowNum() + "Added in List");
					if (validations.isValid(patient)) {
						patient.setStatus("Inducted");
						patientDetailsRepository.save(patient);

					} else if (isPatientNameExit) {
						log.warn("Patient name is already exist");
					} else {
						patientDetailsException.setPatientName(patient.getPatientName());
						patientDetailsException.setExceptionMessage(validations.isValidException(patient));
						exceptions.add(patientDetailsException);
						patientDetailsException.setStatus("Failed");
						patientDetailsExceptionRepository.save(patientDetailsException);

					}
				} else if (row.getRowNum() != 0) {
					patientDetailsException.setPatientName(row.getCell(0).getStringCellValue());
					if (Objects.isNull(row.getCell(1))) {
						patientDetailsException.setExceptionMessage("Patient Address is null");
					} else if (Objects.isNull(row.getCell(2))) {
						patientDetailsException.setExceptionMessage("Patient Date of birth is null");
					} else if (Objects.isNull(row.getCell(3))) {
						patientDetailsException.setExceptionMessage("Patient Email is null");
					} else if (Objects.isNull(row.getCell(4))) {
						patientDetailsException.setExceptionMessage("Patient Contact Number is null");
					} else if (Objects.isNull(row.getCell(5))) {
						patientDetailsException.setExceptionMessage("Patient Drug Id is null");
					} else if (Objects.isNull(row.getCell(6))) {
						patientDetailsException.setExceptionMessage("Patient Drug Name is null");
					}
					exceptions.add(patientDetailsException);
					patientDetailsException.setStatus("Failed");
					patientDetailsExceptionRepository.save(patientDetailsException);
				}
			}

		} catch (IOException | ParseException e) {
			log.error(e.getMessage());
		}
		
		return exceptions;
	}
	// To check empty cell in excel file
	public static boolean isCellEmpty(final Row row) {
		return row.getCell(0) != null && row.getCell(1) != null && row.getCell(2) != null && row.getCell(3) != null
				&& row.getCell(4) != null && row.getCell(5) != null && row.getCell(6) != null;
	}


	@Override
	public PatientDetails getPatientByName(String patientName) {
		PatientDetails patientobj = patientDetailsRepository.findByPatientName(patientName);
		PatientDetails obj = new PatientDetails();
		
		log.info("Printing stutus" + patientobj);
		if (patientobj.getStatus().equals("Inducted")) {

			return patientobj;
		} else {
			return null;
		}
	}

	@Override
	public PatientDetails updatePatientDetails(PatientDetails patientDetails) {
		StringBuilder builder = new StringBuilder();

		PatientDetails updatePatient = patientDetailsRepository.findByPatientName(patientDetails.getPatientName());
		if (updatePatient != null) {
			if (patientDetails.getPatientAddress() != null) {
				updatePatient.setPatientAddress(patientDetails.getPatientAddress());
			}

			if (patientDetails.getPatientDOB() != null && validations.isValidDOB(patientDetails.getPatientDOB())) {
				updatePatient.setPatientDOB(patientDetails.getPatientDOB());
			} else if (patientDetails.getPatientDOB() != null
					&& !validations.isValidDOB(patientDetails.getPatientDOB())) {
				builder.append("Date is not valid Please enter valid format MM-dd-yyyy \n ");
			}

			if (patientDetails.getPatientEmail() != null
					&& validations.isValidEmail(patientDetails.getPatientEmail())) {
				updatePatient.setPatientEmail(patientDetails.getPatientEmail());
			} else if (patientDetails.getPatientEmail() != null
					&& !validations.isValidEmail(patientDetails.getPatientEmail())) {
				builder.append("Please Enter valid Email format!! \n");
			}

			if (patientDetails.getPatientContactNumber() != null
					&& validations.isValidContactNumber(patientDetails.getPatientContactNumber())) {
				updatePatient.setPatientContactNumber(patientDetails.getPatientContactNumber());
			} else if (patientDetails.getPatientContactNumber() != null
					&& !validations.isValidContactNumber(patientDetails.getPatientContactNumber())) {
				builder.append("Please enter valid contact number with 10 Digit Numbers!! \n");
			}

			if (builder.length() == 0) {
				updatePatient.setStatus("Inducted");
				patientDetailsRepository.save(updatePatient);
				
				return updatePatient;

			} else {
				
				return null;

			}

		}

		return null;
	}

}
