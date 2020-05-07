package com.raj.hans.payslip.income;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.raj.hans.payslip.incometax.IncomeTaxRate;
import com.raj.hans.payslip.incometax.IncomeTaxRateTable;

class IncomeCalculatorTest {
	IncomeCalculator incomeCalculator;
	@BeforeEach
	void setUp() throws Exception {
		IncomeTaxRate taxRates[] = {
				new IncomeTaxRate(0, 18200, 0, 0),
				new IncomeTaxRate(18201, 37000, 0.19, 0),
				new IncomeTaxRate(37001, 87000, 0.325, 3572),
				new IncomeTaxRate(87001, 180000, 0.37, 19822),
				new IncomeTaxRate(180001, 999999, 0.45, 54232),
		} ;
		IncomeTaxRateTable incomeTaxRateTable = new IncomeTaxRateTable(taxRates);
		incomeCalculator = new IncomeCalculator(incomeTaxRateTable);
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void testGrossIncome() {
		assertEquals(0, incomeCalculator.grossIncome());
		incomeCalculator.setAnnualIncome(60050);
		assertEquals(5004, incomeCalculator.grossIncome());
		incomeCalculator.setAnnualIncome(95031);
		assertEquals(7919, incomeCalculator.grossIncome());
	}

	@Test
	void testNetIncome() {
		assertEquals(0, incomeCalculator.netIncome());
		incomeCalculator.setAnnualIncome(60050);
		assertEquals(4082, incomeCalculator.netIncome());
		incomeCalculator.setAnnualIncome(120000);
		assertEquals(7331, incomeCalculator.netIncome());
	}

	@Test
	void testIncomeTax() {
		assertEquals(0, incomeCalculator.incomeTax());
		incomeCalculator.setAnnualIncome(60050);
		assertEquals(922, incomeCalculator.incomeTax());
	}
	
	@Test
	void testGetSuper() {
		incomeCalculator.setAnnualIncome(60050);
		assertEquals(0, incomeCalculator.getSuper());
		incomeCalculator.setSuperRate(9);
		incomeCalculator.setAnnualIncome(120000);
		incomeCalculator.setSuperRate(10);
		assertEquals(1000, incomeCalculator.getSuper());
	}
}
