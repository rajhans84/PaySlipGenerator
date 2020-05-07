package com.raj.hans.payslip;

import java.math.BigDecimal;

import com.raj.hans.payslip.income.MonthlyIncomeCalculator;
import com.raj.hans.payslip.incometax.IncomeTaxRateTable;
import com.raj.hans.payslip.input.Employee;
import com.raj.hans.payslip.input.InputInfo;
import com.raj.hans.payslip.money.Money;

public class PaySlipGenerator {
	IncomeTaxRateTable taxRateTable;
	MonthlyIncomeCalculator monthlyIncomeCalculator;

	public PaySlipGenerator(IncomeTaxRateTable taxRateTable) {
		this.taxRateTable = taxRateTable;
		this.monthlyIncomeCalculator = new MonthlyIncomeCalculator(taxRateTable);
	}

	public Paysilp generatePayslipFor(InputInfo inputInfo) {
		Employee emp = inputInfo.getEmployee();
		BigDecimal superRate = inputInfo.getSuperRate();
		String paymentStartDate = inputInfo.getPaymentStartDate();

		monthlyIncomeCalculator.setAnnualIncome(emp.annualSalary);
		monthlyIncomeCalculator.setSuperRate(superRate);

		Money grossIncome = monthlyIncomeCalculator.grossIncome();
		Money incomeTax = monthlyIncomeCalculator.incomeTax();
		Money netIncome = monthlyIncomeCalculator.netIncome();
		Money superAmount = monthlyIncomeCalculator.getSuper();
		
		return new Paysilp(Employee.getFullName(emp), paymentStartDate, grossIncome, incomeTax, netIncome, superAmount);
	}
}
