package com.samples.spring.mvc.rest.service.controllers;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samples.spring.mvc.rest.service.beans.Employee;
import com.samples.spring.mvc.rest.service.utils.EmployeeUtils;

@RestController
public class EmployeeServiceController {
	
	private final static ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping(value="/getEmployee/", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getEmployees() {
		EmployeeUtils utils = EmployeeUtils.getInstance();
		Map<Integer, Employee> employees = utils.getAllEmployees();
		String output = null;
		
		try {
			output = mapper.writeValueAsString(employees);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ResponseEntity<String> response = new ResponseEntity<String>(output, HttpStatus.OK);
		return response;
	}
	
	@RequestMapping(value="/removeEmployee/{id}", method=RequestMethod.GET)
	public ResponseEntity<String> removeEmployees(@PathVariable("id") int id) {
		EmployeeUtils utils = EmployeeUtils.getInstance();
		
		Boolean isDeleted = utils.deleteEmployee(id);
		ResponseEntity<String> response = null;
		
		if(isDeleted) {
			response = new ResponseEntity<String>(HttpStatus.OK);	
		} else {
			response = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
}
