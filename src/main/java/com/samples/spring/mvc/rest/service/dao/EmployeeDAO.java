package com.samples.spring.mvc.rest.service.dao;

import java.util.List;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import com.samples.spring.mvc.rest.service.beans.Employee;

public class EmployeeDAO extends BaseDAO {
	
	private static final ResourceBundle JDBC_PROPS = ResourceBundle.getBundle("jdbc");
	private static final String QUERY_GET_ALL_EMPLOYEES = JDBC_PROPS.getString("query.getAllEmployees");
	private static final String QUERY_GET_EMPLOYEE = JDBC_PROPS.getString("query.getEmployee");
	private static final String QUERY_ADD_EMPLOYEE = JDBC_PROPS.getString("query.addEmployee");
	private static final String QUERY_REMOVE_EMPLOYEE = JDBC_PROPS.getString("query.removeEmployee");
	
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public List<Employee> getAllEmployees() {
		List<Employee> employees = (List<Employee>) jdbcTemplate.query(QUERY_GET_ALL_EMPLOYEES, new EmployeeDataExtractor());
		return employees;
	}
	
	public Employee getEmployee(int id) {
		List<Employee> employees = null;
		Employee employee = null;
		employees = (List<Employee>) jdbcTemplate.query(QUERY_GET_EMPLOYEE, new Object[] {id}, new EmployeeDataExtractor());
		
		if(employees != null && !employees.isEmpty()) {
			employee = employees.get(0);
		}
		
		return employee;
	}

	public void add(String name, String address, int age) {
		jdbcTemplate.update(QUERY_ADD_EMPLOYEE, new Object[] {name, address, age});
	}
	
	public boolean remove(int id) {
		boolean isDeleted = false;
		int rowsAffected = jdbcTemplate.update(QUERY_REMOVE_EMPLOYEE, new Object[] {id});
		if(rowsAffected > 0) {
			isDeleted = true;
		}
		return isDeleted;
	}
	
}
