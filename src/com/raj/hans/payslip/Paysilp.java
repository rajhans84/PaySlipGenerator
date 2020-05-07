package com.raj.hans.payslip;

import com.raj.hans.payslip.money.Money;

public class Paysilp {
	String name;
	String paymentStartDate;
	Money grossIncome;
	Money incomeTax;
	Money netIncome;
	Money superAmount;
	public Paysilp(String name, String paymentStartDate, Money grossIncome, Money incomeTax, Money netIncome,
			Money superAmount) {
		this.name = name;
		this.paymentStartDate = paymentStartDate;
		this.grossIncome = grossIncome;
		this.incomeTax = incomeTax;
		this.netIncome = netIncome;
		this.superAmount = superAmount;
	}
	public String getName() {
		return name;
	}
	public String getPaymentStartDate() {
		return paymentStartDate;
	}
	public Money getGrossIncome() {
		return grossIncome;
	}
	public Money getIncomeTax() {
		return incomeTax;
	}
	public Money getNetIncome() {
		return netIncome;
	}
	public Money getSuperAmount() {
		return superAmount;
	}
}
