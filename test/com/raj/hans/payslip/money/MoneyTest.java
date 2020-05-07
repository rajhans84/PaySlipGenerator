package com.raj.hans.payslip.money;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoneyTest {
	Money m1, m2, m3, m4, m5, m6;

	@BeforeEach
	void setUp() throws Exception {
		m1 = new Money("5004.16666667");
		m2 = new Money("921.9375");
		m3 = new Money("450.36");
		m4 = new Money(-134);
		m5 = Money.getMoneyThatHasZeroValue();
		m6 = Money.getInfiniteMoney();

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testMoneyRouding() {
		assertEquals(5004, m1.intValue());
		assertEquals(922, m2.intValue());
		assertEquals(450, m3.intValue());
	}

	@Test
	void testMoneyAdd() {
		m3 = m1.add(m2);
		assertEquals(5926, m3.intValue());
	}

	@Test
	void testMoneySubtract() {
		m3 = m2.subtract(m1);
		assertEquals(-4082, m3.intValue());
	}

	@Test
	void testMoneyMultiply() {
		assertEquals(1844, m2.multiply(new BigDecimal("2")).intValue());
	}

	@Test
	void testMoneyMultiplyAndRound() {
		m3 = m2.multiplyAndRound(new BigDecimal("2"));
		assertEquals(1844, m3.intValue());
	}
	
	@Test
	void testMoneyDivide() {
		assertEquals(2502, m1.divide(new BigDecimal("2")).intValue());
	}
	
	@Test
	void testMoneyDivideAndReturnMoney() {
		m3 = m2.divideAndReturnMoney(new BigDecimal("2"));
		assertEquals(461, m3.intValue());
	}
	
	@Test
	void testMoneyPercentageByRate() {
		m3 = m1.percentageByRate(new BigDecimal(9));
		assertEquals(450, m3.intValue());
	}

	@Test
	void testMoneyComparison() {
		assertTrue(m1.gt(m2));
		assertTrue(m1.gtEq(m1));
		assertTrue(m3.lt(m2));
		assertTrue(m3.ltEq(m3));
		assertTrue(m3.gtZero());
		assertTrue(m4.ltZero());
		assertTrue(m5.ltEqZero());
		assertTrue(m6.gt(m1));
	}
}
