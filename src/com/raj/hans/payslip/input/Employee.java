package com.raj.hans.payslip.input;

import com.raj.hans.payslip.money.Money;

public class Employee {
	public String firstName;
	public String lastName;
	public String fullName;
	public Money annualSalary;
	public Employee(String firstName, String lastName, Money annualSalary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.annualSalary = annualSalary;
	}
	
	public static String getFullName(Employee employee) {
		return employee.firstName + " " + employee.lastName;
 	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Money getAnnualSalary() {
		return annualSalary;
	}
	
}
