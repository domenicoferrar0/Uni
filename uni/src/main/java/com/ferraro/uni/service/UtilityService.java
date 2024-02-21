package com.ferraro.uni.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class UtilityService {

	public Map<String, String> getFieldErrors(BindingResult result) {
		Map<String, String> errors = new HashMap<String, String>();
		List<FieldError> fieldErrors = result.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return errors;
	}

	public String generaMatricola(Long id) {
		String prefix = "00";
		if (id > 9L) {
			prefix = "0";
		}
		else if(id>100L) {
			prefix = "";
		}
		prefix = prefix+id.toString();
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		String cod = String.format("%06d", number);
		
		return prefix + cod;

	}
	
	public String generaCodiceDocente() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		String cod = String.format("%06d", number);
		return "DC"+cod;
	}
	
	public Map<String, String> fieldError(String field, String message) {
		Map<String, String> error = new HashMap<String, String>();
		error.put(field, message);
		return error;
	}
	
}
