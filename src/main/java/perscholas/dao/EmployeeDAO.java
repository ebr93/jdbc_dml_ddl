package perscholas.dao;

import java.util.List;

import perscholas.models.Employees;

public interface EmployeeDAO {
	// crud create, read, update, delete
	
	int createEmployee(Employees employee);
	List<Employees> getAllEmployees();
	int updateEmployee(Employees employee);
	int deleteEmployee(int empID);
}
