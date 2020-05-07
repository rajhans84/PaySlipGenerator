package com.raj.hans.payslip.test.incometax;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.raj.hans.payslip.incometax.IncomeTaxRate;

class IncomeTaxSlabsTableTest {
	
	List<Integer> lowerBound;
	List<Integer> upperBound; 
	List<Double> taxPercentage;
	List<Double> expectedAmount;
	
	@BeforeEach
	void setUp() throws Exception {
		lowerBound = Arrays.asList(0, 18201, 37001, 87001, 180001);
		upperBound = Arrays.asList(18200, 37000, 87000, 180000, 999999);
		taxPercentage = Arrays.asList(0.0, 0.19, 0.325, 0.37, 0.45);
		expectedAmount = Arrays.asList(0.0, 3572.00, 19822.00, 54232.00, 369000.00);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCalculateTaxWithinRange() {
		
		IncomeTaxRate taxRateTable[] = {
				new IncomeTaxRate(0, 18200, 0, 0),
				new IncomeTaxRate(18201, 37000, 0.19, 0),
				new IncomeTaxRate(37001, 87000, 0.325, 3572),
				new IncomeTaxRate(87001, 180000, 0.37, 19822),
				new IncomeTaxRate(180001, 999999, 0.45, 54232),
		} ;
		
		assertEquals(taxRateTable[2].getLow(), 37001);
		assertEquals(taxRateTable[2].getHigh(), 87000);
	}

}
