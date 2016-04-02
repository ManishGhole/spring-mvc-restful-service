package com.samples.spring.mvc.rest.service.utils;

import java.util.HashMap;
import java.util.Map;

import com.samples.spring.mvc.rest.service.beans.Employee;

public class EmployeeUtils {
	private final static EmployeeUtils selfInstance = new EmployeeUtils();
	
	public static EmployeeUtils getInstance() {
		return selfInstance;
	}
	
	private Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
	
	private EmployeeUtils() {
		Employee emp1 = new Employee();
		emp1.setName("Manish");
		emp1.setAge(32);
		emp1.setAddress("Phoenix, AZ");
		employees.put(1, emp1);
		
		Employee emp2 = new Employee();
		emp2.setName("Aarti");
		emp2.setAge(30);
		emp2.setAddress("Peoria, AZ");
		employees.put(2, emp2);
	}
	
	public Map<Integer, Employee> getAllEmployees() {
		return employees;
	}
	
	public boolean deleteEmployee(int id) {
		boolean isDeleted = false;
		if(employees.containsKey(id)) {
			employees.remove(id);
			isDeleted = true;
		}
		return isDeleted;
	}
	
}
