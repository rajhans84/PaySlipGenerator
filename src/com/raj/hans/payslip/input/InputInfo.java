package com.raj.hans.payslip.input;

import java.math.BigDecimal;

public class InputInfo {
	Employee employee;
	String paymentStartDate;
	BigDecimal superRate;
	public InputInfo(Employee employee, String paymentStartDate, BigDecimal superRate) {
		this.employee = employee;
		this.paymentStartDate = paymentStartDate;
		this.superRate = superRate;
	}
	public Employee getEmployee() {
		return employee;
	}
	public String getPaymentStartDate() {
		return paymentStartDate;
	}
	public BigDecimal getSuperRate() {
		return superRate;
	}
}
