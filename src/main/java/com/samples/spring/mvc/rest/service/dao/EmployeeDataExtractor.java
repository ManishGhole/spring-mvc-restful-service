package com.samples.spring.mvc.rest.service.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.samples.spring.mvc.rest.service.beans.Employee;

public class EmployeeDataExtractor implements ResultSetExtractor<List<Employee>> {

	public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Employee> employees = new ArrayList<Employee>();
		
		while(rs.next()) {
			Employee emp = new Employee();
			emp.setId(rs.getInt("id"));
			emp.setName(rs.getString("name"));
			emp.setAddress(rs.getString("address"));
			emp.setAge(rs.getInt("age"));
			employees.add(emp);
		}
		
		return employees;
	}

}
