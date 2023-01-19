package perscholas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

import perscholas.models.Employees;
import perscholas.service.EmployeeService;

public class Main {

	public static void main(String[] args) {
		// this will start your main program
		EmployeeService es = new EmployeeService();
		System.out.println(es.getAllEmployees());
		System.out.println(es.getEmployeeEmails());
		Employees newEmployee = new Employees(0004, "Barcenas", "Eduardo", "x9999", "barcenased@classicmodelscars.com", "1", 1002, "software engineer", 60);
		// System.out.println(es.updateEmployee(newEmployee));
		// System.out.println(es.deleteEmployee(3));

	}

}
