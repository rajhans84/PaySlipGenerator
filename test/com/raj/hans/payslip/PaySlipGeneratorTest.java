package com.raj.hans.payslip;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.raj.hans.payslip.incometax.IncomeTaxRate;
import com.raj.hans.payslip.incometax.IncomeTaxRateTable;
import com.raj.hans.payslip.input.Employee;
import com.raj.hans.payslip.input.InputInfo;
import com.raj.hans.payslip.money.Money;

class PaySlipGeneratorTest {
	
	PaySlipGenerator payslipGenerator;

	@BeforeEach
	void setUp() throws Exception {
		IncomeTaxRate taxRates[] = { new IncomeTaxRate(0, 18200, 0, 0), new IncomeTaxRate(18201, 37000, 0.19, 0),
				new IncomeTaxRate(37001, 87000, 0.325, 3572), new IncomeTaxRate(87001, 180000, 0.37, 19822),
				new IncomeTaxRate(180001, 999999, 0.45, 54232), };
		IncomeTaxRateTable incomeTaxRateTable = new IncomeTaxRateTable(taxRates);
		payslipGenerator = new PaySlipGenerator(incomeTaxRateTable);
	}

	@Test
	void testGeneratePayslipFor() {
		Employee employee = new Employee("Ryan", "Chen", new Money(120000));
        InputInfo inputInfo = new InputInfo(employee, "01 March - 31 March", new BigDecimal(10));
        Paysilp actualPayslip = payslipGenerator.generatePayslipFor(inputInfo);
        
        assertNotNull(actualPayslip);
        assertEquals("Ryan Chen",actualPayslip.getName());
        assertEquals("01 March - 31 March", actualPayslip.getPaymentStartDate());
        assertEquals(10000, actualPayslip.getGrossIncome().intValue());
        assertEquals(2669, actualPayslip.getIncomeTax().intValue());
        assertEquals(7331, actualPayslip.getNetIncome().intValue());
        assertEquals(1000, actualPayslip.getSuperAmount().intValue());
	}

}
