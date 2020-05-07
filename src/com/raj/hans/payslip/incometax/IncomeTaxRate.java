package com.raj.hans.payslip.incometax;

import java.math.BigDecimal;

import com.raj.hans.payslip.money.Money;

public class IncomeTaxRate {
	private Money low; 
	private Money high;
	private BigDecimal rate;
	private Money offset;
	public IncomeTaxRate(int low, int high, double rate, int offset) {
		this.low = new Money(low);
		this.high = new Money(high);
		this.rate = new BigDecimal(rate);
		this.offset = new Money(offset);
	}
	public Money getLow() {
		return low;
	}
	public Money getHigh() {
		return high;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public Money getOffset() {
		return offset;
	}
}
