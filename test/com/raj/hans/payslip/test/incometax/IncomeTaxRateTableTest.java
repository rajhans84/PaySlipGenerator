package com.raj.hans.payslip.test.incometax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.raj.hans.payslip.incometax.IncomeTaxRate;
import com.raj.hans.payslip.incometax.IncomeTaxRateTable;
import com.raj.hans.payslip.money.Money;
class IncomeTaxRateTableTest {
	IncomeTaxRate taxRates[];
	IncomeTaxRateTable table;
	
	@BeforeEach
	void setUp() throws Exception {
		 taxRates = new IncomeTaxRate[]{
				new IncomeTaxRate(0, 18200, 0, 0),
				new IncomeTaxRate(18201, 37000, 0.19, 0),
				new IncomeTaxRate(37001, 87000, 0.325, 3572),
				new IncomeTaxRate(87001, 180000, 0.37, 19822),
				new IncomeTaxRate(180001, 999999, 0.45, 54232),
		} ;
		 table = new IncomeTaxRateTable(taxRates);
	}

	@Test
	void testGetByIndex() {
		IncomeTaxRateTable table = new IncomeTaxRateTable(taxRates);
		IncomeTaxRate expectedRate = taxRates[3];
		assertEquals(expectedRate, table.getByIndex(3));
	}
	
	@Test
	void testGetByIncome_LowerRange() {
		IncomeTaxRate expectedRate = taxRates[1];
		assertEquals(expectedRate, table.getByIncome(new Money(37000)));
	}

	@Test
	void testGetByIncome_MidRange() {
		IncomeTaxRate expectedRate = taxRates[2];
		assertEquals(expectedRate, table.getByIncome(new Money(67000)));
	}
	
	@Test
	void testGetByIncome_HighRange() {
		IncomeTaxRate expectedRate = taxRates[3];
		assertEquals(expectedRate, table.getByIncome(new Money(178000)));
	}
	@Test
	void testGetByIncome_NotFound() {
		assertEquals(null, table.getByIncome(new Money(9178000)));
	}
}
