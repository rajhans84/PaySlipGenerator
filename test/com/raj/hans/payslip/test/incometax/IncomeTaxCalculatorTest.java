package com.raj.hans.payslip.test.incometax;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.raj.hans.payslip.incometax.IncomeTaxCalculator;
import com.raj.hans.payslip.incometax.IncomeTaxRate;
import com.raj.hans.payslip.incometax.IncomeTaxRateTable;
import com.raj.hans.payslip.money.Money;

class IncomeTaxCalculatorTest {
	IncomeTaxCalculator taxCalc;
	
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
		taxCalc = new IncomeTaxCalculator(incomeTaxRateTable);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testIncomeTaxForMonth_case1() {
		assertEquals(922, taxCalc.incomeTaxForMonth(new Money(60050)).intValue());
	}

	@Test
	void testIncomeTaxForMonth_case2() {
		assertEquals(0, taxCalc.incomeTaxForMonth(new Money(10050)).intValue());
	}
	
	@Test
	void testIncomeTaxForMonth_case3() {
		assertEquals(2669, taxCalc.incomeTaxForMonth(new Money(120000)).intValue());
	}
}
