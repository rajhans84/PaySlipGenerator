package com.raj.hans.payslip.incometax;

public class IncomeTaxCalculator {
	private IncomeTaxRateTable incomeTaxRateTable;
	private IncomeTaxRate applicableRate;
	public IncomeTaxCalculator(IncomeTaxRateTable incomeTaxRateTable) {
		super();
		this.incomeTaxRateTable = incomeTaxRateTable;
	}
	
	private double annualTax(int annualIncome) {
		applicableRate =  this.incomeTaxRateTable.getByIncome(annualIncome);
		return applicableRate.getOffset() + slabTax(annualIncome);
	}

	public int incomeTaxForMonth(int annualIncome) {
		return (int) Math.round(annualTax(annualIncome) / 12);
	}
	
	private double slabTax(int annualIncome) {
		return (annualIncome - applicableRate.getLow()) * applicableRate.getRate();
	}
}
