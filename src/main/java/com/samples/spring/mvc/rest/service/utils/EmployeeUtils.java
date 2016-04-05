package com.samples.spring.mvc.rest.service.utils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.samples.spring.mvc.rest.service.beans.Employee;
import com.samples.spring.mvc.rest.service.dao.EmployeeDAO;

public class EmployeeUtils {
	private static EmployeeUtils selfInstance = null;
	private static ApplicationContext context = null;
	private static final String EMPLOYEE_DAO_BEAN_NAME = "employeeDataDAO";
	
	private EmployeeDAO employeeDAO;
	private AtomicInteger idCounter = new AtomicInteger(0);
	
	static {
		init();
	}
	
	private static void init() {
		try {
			context = new ClassPathXmlApplicationContext("spring_db_config.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private EmployeeUtils() {
		employeeDAO = (EmployeeDAO) context.getBean(EMPLOYEE_DAO_BEAN_NAME);
	}
	
	public static EmployeeUtils getInstance() {
		if(selfInstance == null) {
			selfInstance = new EmployeeUtils();	
		}
		return selfInstance;
	}
	
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeDAO.getAllEmployees();
		return employees;
	}
	
	public Employee getEmployee(int id) {
		Employee employee = employeeDAO.getEmployee(id);
		return employee;
	}
	
	public void addEmployee(Employee employee) {
		employeeDAO.add(employee.getName(), employee.getAddress(), employee.getAge());
	}
	
	public boolean removeEmployee(int id) {
		boolean isDeleted = employeeDAO.remove(id);
		return isDeleted;
	}
	
}
