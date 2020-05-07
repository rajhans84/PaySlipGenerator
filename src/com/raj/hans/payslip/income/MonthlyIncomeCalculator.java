package com.raj.hans.payslip.income;

import java.math.BigDecimal;

import com.raj.hans.payslip.incometax.IncomeTaxCalculator;
import com.raj.hans.payslip.incometax.IncomeTaxRateTable;
import com.raj.hans.payslip.money.Money;

public class MonthlyIncomeCalculator {
	Money annualIncome;
	BigDecimal superRate;
	IncomeTaxRateTable taxRateTable;
	
	public MonthlyIncomeCalculator(IncomeTaxRateTable taxRateTable) {
		this.taxRateTable = taxRateTable;
	}
	
	public Money grossIncome() {
		if(annualIncome == null) {
			throw new IllegalArgumentException("Annual Income cannot be null");
		}
		return annualIncome.divideByInt(12);
	}
	
	public Money incomeTax() {
		if(annualIncome == null) {
			throw new IllegalArgumentException("Annual Income cannot be null");
		}
		IncomeTaxCalculator taxCalc = new IncomeTaxCalculator(taxRateTable);
		return taxCalc.incomeTaxForMonth(annualIncome);
	}

	public Money netIncome() {
		if(annualIncome == null) {
			throw new IllegalArgumentException("Annual Income cannot be null");
		}
		return grossIncome().subtract(incomeTax());
	}

	public Money getSuper() {
		if(superRate == null) {
			throw new IllegalArgumentException("Super Rate cannot be null");
		}
		return grossIncome().percentageByRate(superRate);
	}

	public void setAnnualIncome(Money annualIncome) {
		this.annualIncome = annualIncome;
	}

	public void setSuperRate(BigDecimal superRate) {
		this.superRate = superRate;
	}
}
