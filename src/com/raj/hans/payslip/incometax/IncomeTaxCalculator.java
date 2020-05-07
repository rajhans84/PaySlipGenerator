package com.raj.hans.payslip.incometax;

import java.math.BigDecimal;

import com.raj.hans.payslip.money.Money;

public class IncomeTaxCalculator {
	private IncomeTaxRateTable incomeTaxRateTable;
	private IncomeTaxRate applicableRate;
	public IncomeTaxCalculator(IncomeTaxRateTable incomeTaxRateTable) {
		this.incomeTaxRateTable = incomeTaxRateTable;
	}
	
	private Money annualTax(Money annualIncome) {
		applicableRate =  this.incomeTaxRateTable.getByIncome(annualIncome);
		return applicableRate.getOffset().add(slabTax(annualIncome));
	}

	public Money incomeTaxForMonth(Money annualIncome) {
		Money monthlyIncomeTax = annualTax(annualIncome).divideAndReturnMoney(new BigDecimal(12));
		return monthlyIncomeTax;
	}
	
	private Money slabTax(Money annualIncome) {
		Money diff = annualIncome.subtract(applicableRate.getLow());
		BigDecimal taxRate = applicableRate.getRate();
		return diff.multiplyAndRound(taxRate);
	}
}
