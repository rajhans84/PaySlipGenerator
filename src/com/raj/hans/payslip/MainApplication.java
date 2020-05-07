package com.raj.hans.payslip;

import com.raj.hans.payslip.incometax.IncomeTaxRate;
import com.raj.hans.payslip.incometax.IncomeTaxRateTable;

public class MainApplication {

	public static void main(String[] args) {
		
		IncomeTaxRateTable taxRateTable = new IncomeTaxRateTable(
				new IncomeTaxRate[]{
				new IncomeTaxRate(0, 18200, 0, 0),
				new IncomeTaxRate(18201, 37000, 0.19, 0),
				new IncomeTaxRate(37001, 87000, 0.325, 3572),
				new IncomeTaxRate(87001, 180000, 0.37, 19822),
				new IncomeTaxRate(180001, 999999, 0.45, 54232),
		}) ;
	}
}
