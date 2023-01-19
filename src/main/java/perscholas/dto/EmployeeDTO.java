package perscholas.dto;

import java.util.Objects;

public class EmployeeDTO {
	// fields
	private int employeeNumber;
	private String email;
	public EmployeeDTO(int employeeNumber, String email) {
		super();
		this.employeeNumber = employeeNumber;
		this.email = email;
	}
	public int getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	// toString, hash, equals
	@Override
	public String toString() {
		return "EmployeeDTO [employeeNumber=" + employeeNumber + ", email=" + email + "]\n";
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, employeeNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDTO other = (EmployeeDTO) obj;
		return Objects.equals(email, other.email) && employeeNumber == other.employeeNumber;
	}
	
	
}
