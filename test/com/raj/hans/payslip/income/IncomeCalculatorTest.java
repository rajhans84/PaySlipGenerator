package com.raj.hans.payslip.income;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.raj.hans.payslip.incometax.IncomeTaxRate;
import com.raj.hans.payslip.incometax.IncomeTaxRateTable;
import com.raj.hans.payslip.money.Money;

class IncomeCalculatorTest {
	MonthlyIncomeCalculator incomeCalculator;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@BeforeEach
	void setUp() throws Exception {
		IncomeTaxRate taxRates[] = { new IncomeTaxRate(0, 18200, 0, 0), new IncomeTaxRate(18201, 37000, 0.19, 0),
				new IncomeTaxRate(37001, 87000, 0.325, 3572), new IncomeTaxRate(87001, 180000, 0.37, 19822),
				new IncomeTaxRate(180001, 999999, 0.45, 54232), };
		IncomeTaxRateTable incomeTaxRateTable = new IncomeTaxRateTable(taxRates);
		incomeCalculator = new MonthlyIncomeCalculator(incomeTaxRateTable);
	}

	@AfterEach
	void tearDown() throws Exception {

	}

	@Test
	void testGrossIncomeThrowsExceptionIfNoIncomeSet() {
		exception.expect(IllegalArgumentException.class);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			incomeCalculator.grossIncome();
		});
		String expectedMessage = "Annual Income cannot be null";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testGrossIncome() {
		incomeCalculator.setAnnualIncome(new Money(60050));
		assertEquals(5004, incomeCalculator.grossIncome().intValue());
		incomeCalculator.setAnnualIncome(new Money(95031));
		assertEquals(7919, incomeCalculator.grossIncome().intValue());
	}

	@Test
	void testNetIncome() {
		incomeCalculator.setAnnualIncome(new Money(60050));
		assertEquals(4082, incomeCalculator.netIncome().intValue());
		incomeCalculator.setAnnualIncome(new Money(120000));
		assertEquals(7331, incomeCalculator.netIncome().intValue());
	}

	@Test
	void testIncomeTax() {
		incomeCalculator.setAnnualIncome(new Money(60050));
		assertEquals(922, incomeCalculator.incomeTax().intValue());
	}

	@Test
	void testGetSuperThrowsExceptionIfNoRateSet() {
		incomeCalculator.setAnnualIncome(new Money(60050));
		exception.expect(IllegalArgumentException.class);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			incomeCalculator.getSuper();
		});
		String expectedMessage = "Super Rate cannot be null";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testGetSuper() {
		incomeCalculator.setAnnualIncome(new Money(60050));
		incomeCalculator.setSuperRate(new BigDecimal(9));
		assertEquals(450, incomeCalculator.getSuper().intValue());
		incomeCalculator.setAnnualIncome(new Money(120000));
		incomeCalculator.setSuperRate(new BigDecimal(10));
		assertEquals(1000, incomeCalculator.getSuper().intValue());
	}
}
