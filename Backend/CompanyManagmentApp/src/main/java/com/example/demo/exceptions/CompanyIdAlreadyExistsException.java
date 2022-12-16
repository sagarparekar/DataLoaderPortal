package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT, reason="The Company ID is already present in Databae")
public class CompanyIdAlreadyExistsException extends Exception{

}
