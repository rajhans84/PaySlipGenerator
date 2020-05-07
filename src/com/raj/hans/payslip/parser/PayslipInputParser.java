package com.raj.hans.payslip.parser;

import java.math.BigDecimal;

import com.raj.hans.payslip.input.Employee;
import com.raj.hans.payslip.input.InputInfo;
import com.raj.hans.payslip.money.Money;

public class PayslipInputParser {
	private static final String SEPARATOR = ",";

	public InputInfo readCSVInput(String inputLine) {
		String[] values = inputLine.split(SEPARATOR);

		String firstName = values[0];

		String lastName = values[1];

		Money annualSalary = validateAnnualSalary(values[2]);

		Employee employee = new Employee(firstName, lastName, annualSalary);

		BigDecimal superRate = parseSuperRate(values[3]);

		String paymentStartDate = values[4];

		return new InputInfo(employee, paymentStartDate, superRate);

	}

	public BigDecimal parseSuperRate(String input) {
		BigDecimal superRate = ParserUtils.parseAsRate(input);
		if (superRate.compareTo(BigDecimal.ZERO) < 0 || superRate.compareTo(BigDecimal.valueOf(50)) > 0) {
			throw new IllegalArgumentException("Invalid Super Rate");
		}
		return superRate;
	}

	public Money validateAnnualSalary(String input) {
		Money anualSal = new Money(input);
		if (anualSal.ltZero() || anualSal.gt(Money.getInfiniteMoney())) {
			throw new IllegalArgumentException("Invalid Annual Salary");
		}
		return anualSal;
	}
}
