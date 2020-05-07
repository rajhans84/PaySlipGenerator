package com.raj.hans.payslip.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.raj.hans.payslip.Paysilp;
import com.raj.hans.payslip.money.Money;

class OutputWriterTest {
	OutputWriter outputWriter;

	@BeforeEach
	void setUp() throws Exception {
		outputWriter = new OutputWriter();
	}

	@Test
	void testwriteCsv() {
		Paysilp payslip = new Paysilp("David Rudd", "01 March - 31 March", new Money(5004), new Money(922),
				new Money(4082), new Money(450));
		String actual = outputWriter.writeCsv(payslip);
		assertEquals("David Rudd,01 March - 31 March,5004,922,4082,450", actual);
	}
}
