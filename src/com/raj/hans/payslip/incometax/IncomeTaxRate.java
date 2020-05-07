package com.raj.hans.payslip.incometax;

public class IncomeTaxRate {
	private int low; 
	private int high;
	private double rate;
	private int offset;
	public IncomeTaxRate(int low, int high, double rate, int offset) {
		super();
		this.low = low;
		this.high = high;
		this.rate = rate;
		this.offset = offset;
	}
	public int getLow() {
		return low;
	}
	public int getHigh() {
		return high;
	}
	public double getRate() {
		return rate;
	}
	public int getOffset() {
		return offset;
	}
}
