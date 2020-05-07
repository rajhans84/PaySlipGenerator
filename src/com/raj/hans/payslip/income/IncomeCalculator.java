package com.raj.hans.payslip.income;

import com.raj.hans.payslip.incometax.IncomeTaxCalculator;
import com.raj.hans.payslip.incometax.IncomeTaxRateTable;

public class IncomeCalculator {
	int annualIncome;
	int superRate;
	IncomeTaxRateTable taxRateTable;
	
	public IncomeCalculator(IncomeTaxRateTable taxRateTable) {
		this.taxRateTable = taxRateTable;
	}
	
	public int grossIncome() {
		return Math.round(annualIncome / 12);
	}
	
	public int incomeTax() {
		IncomeTaxCalculator taxCalc = new IncomeTaxCalculator(taxRateTable);
		return taxCalc.incomeTaxForMonth(annualIncome);
	}

	public int netIncome() {
		return grossIncome() - incomeTax();
	}

	public int getSuper() {
		if(superRate < 0 || superRate > 50) {
			throw new IllegalArgumentException("Super rate should be between 0 and 50");
		}
		return Math.round((grossIncome() * superRate)/100);
	}

	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
	}

	public void setSuperRate(int superRate) {
		this.superRate = superRate;
	}
}
