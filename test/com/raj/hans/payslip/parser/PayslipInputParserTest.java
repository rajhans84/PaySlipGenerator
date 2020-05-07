package com.raj.hans.payslip.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.raj.hans.payslip.input.InputInfo;

class PayslipInputParserTest {
	PayslipInputParser parser;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@BeforeEach
	void setUp() throws Exception {
		parser = new PayslipInputParser();
	}

	@Test
	void testReadCSVInput() {
		InputInfo payslipInputInfo = parser.readCSVInput("David,Rudd,60050,9%,01 March – 31 March");

		assertEquals("David", payslipInputInfo.getEmployee().getFirstName());
		assertEquals("Rudd", payslipInputInfo.getEmployee().getLastName());
		assertEquals("01 March – 31 March", payslipInputInfo.getPaymentStartDate());
		assertEquals(BigDecimal.valueOf(9), payslipInputInfo.getSuperRate());
		assertEquals(60050, payslipInputInfo.getEmployee().getAnnualSalary().intValue());
	}

	@Test
	public void testReadCSVInput_throwsException_superRate_out_of_range() {
		exception.expect(IllegalArgumentException.class);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			parser.readCSVInput("David,Rudd,60050,900%,01 March – 31 March");
		});
		String expectedMessage = "Invalid Super Rate";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testReadCSVInput_throwsException_annualIncome_out_of_range() {
		exception.expect(IllegalArgumentException.class);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			parser.readCSVInput("David,Rudd,-60050,9%,01 March – 31 March");
		});
		String expectedMessage = "Invalid Annual Salary";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
}
