package com.samples.spring.mvc.rest.service.controllers;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
		String output = null;
		ResponseEntity<String> response = null;
		
		try {
			List<Employee> employees = utils.getAllEmployees();
			if(employees != null && !employees.isEmpty()) {
				output = mapper.writeValueAsString(employees);
				response = new ResponseEntity<String>(output, HttpStatus.OK);
			} else {
				response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
	
	@RequestMapping(value="/getEmployee/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getEmployees(@PathVariable("id") int id) {
		EmployeeUtils utils = EmployeeUtils.getInstance();
		String output = null;
		ResponseEntity<String> response = null;
		
		try {
			Employee employee = utils.getEmployee(id);
			if(employee != null) {
				output = mapper.writeValueAsString(employee);
				response = new ResponseEntity<String>(output, HttpStatus.OK);
			} else {
				response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value="/addEmployee/", method=RequestMethod.POST)
	public ResponseEntity<String> addEmployees(@RequestBody String jsonData) {
		EmployeeUtils utils = EmployeeUtils.getInstance();
		ResponseEntity<String> response = null;
		
		try {
			Employee employee = mapper.readValue(jsonData, Employee.class);
			utils.addEmployee(employee);
			response = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}
	
	@RequestMapping(value="/removeEmployee/{id}", method=RequestMethod.GET)
	public ResponseEntity<String> removeEmployees(@PathVariable("id") int id) {
		EmployeeUtils utils = EmployeeUtils.getInstance();
		ResponseEntity<String> response = null;
		
		try {
			Boolean isDeleted = utils.removeEmployee(id);	
			if(isDeleted) {
				response = new ResponseEntity<String>(HttpStatus.OK);	
			} else {
				response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			response = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return response;
	}
}
