package perscholas.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import perscholas.dao.EmployeeDAO;
import perscholas.dto.EmployeeDTO;
import perscholas.models.Employees;

public class EmployeeService implements EmployeeDAO {
	static final String DB_URL = "jdbc:mysql://localhost/classicmodels";
	static final String USER = "root";
	static final String PASSWORD = "root";
	// crud create, read, update, delete

	@Override
	public int createEmployee(Employees employee) {
		int affectedRows = 0;
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
			// prepared statement
			PreparedStatement ps = conn.prepareStatement("INSERT INTO employees (employee_id, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle, VacationHours) VALUES (?,?,?,?,?,?,?,?,?)");
	        //String sqlQuery = "INSERT INTO EMPLOYEES (officeCode,firstName,lastName,email,extension,reportsTo,VacationHours,employee_id,jobTitle) VALUES (?,?,?,?,?,?,?,?,?)";
			//PreparedStatement ps = conn.prepareStatement(sqlQuery);
	        // result set
			ps.setInt(1, employee.getEmployeeNumber());
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getFirstName());
			ps.setString(4, employee.getExtension());
			ps.setString(5, employee.getEmail());
			ps.setString(6, employee.getOfficeCode());
			ps.setInt(7, employee.getReportsTo());
			ps.setString(8, employee.getJobTitle());
			ps.setInt(9, employee.getVacationHours());
			affectedRows = ps.executeUpdate();
			System.out.println(affectedRows + " rows(s) affected !!");
		} catch (SQLException ex) {
			ex.printStackTrace(); 
		}
		return affectedRows;
	}

	@Override
	public List<Employees> getAllEmployees() {
		List<Employees> listEmp = new ArrayList<>();
		// connection
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
			// prepared statement
			PreparedStatement ps = conn.prepareStatement("select * from employees");
			// result set
			ResultSet rs = ps.executeQuery();
			// convert result set to employee object
			while(rs.next()) {
				// int employeeNumber, String lastName, String firstName, String extension, String email,
				// String officeCode, int reportsTo, String jobTitle, int vacationHours
				// convert data into an Employees object
				Employees e = new Employees(
						rs.getInt("employee_id"),
						rs.getString("lastName"),
						rs.getString("firstName"),
						rs.getString("extension"),
						rs.getString("email"),
						rs.getString("officeCode"),
						rs.getInt("reportsTo"),
						rs.getString("jobTitle"),
						rs.getInt("VacationHours")
						);
				listEmp.add(e);
			}
			// add object into list
		} catch (SQLException ex) {
			ex.printStackTrace(); 
		}
		return listEmp;
	}

	@Override
	public int updateEmployee(Employees employee) {
		int affectedRows = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String updateSQL = "update employees set firstName=? , lastName=? where employee_id=?";
            PreparedStatement ps = conn.prepareStatement(updateSQL);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setInt(3, employee.getEmployeeNumber());
            affectedRows =  ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return affectedRows;
	}

	@Override
	public int deleteEmployee(int empID) {
		int affectedRows = 0;
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
			String deleteSQL = "delete from employees where employee_id=?";
			PreparedStatement ps = conn.prepareStatement(deleteSQL);
			ps.setInt(1, empID);
			affectedRows = ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// TODO Auto-generated method stub
		return affectedRows;
	}
	
	public List<EmployeeDTO> getEmployeeEmails() {
		List<EmployeeDTO> listEmp = new ArrayList<>();
		// connection
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
			// prepared statement
			PreparedStatement ps = conn.prepareStatement("select * from employees");
			// result set
			ResultSet rs = ps.executeQuery();
			// convert result set to employee object
			while(rs.next()) {
				// int employeeNumber, String lastName, String firstName, String extension, String email,
				// String officeCode, int reportsTo, String jobTitle, int vacationHours
				// convert data into an Employees object
				EmployeeDTO e = new EmployeeDTO(
						rs.getInt("employee_id"),
						rs.getString("email")
						);
				listEmp.add(e);
			}
			// add object into list
		} catch (SQLException ex) {
			ex.printStackTrace(); 
		}
		return listEmp;
	}

}
