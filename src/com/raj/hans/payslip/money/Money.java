package com.raj.hans.payslip.money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.annotation.processing.RoundEnvironment;

public class Money implements Comparable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7300339745634312581L;
	private static final String ZERO_STRING = "0";
	private static final Money ZERO_MONEY = new Money(ZERO_STRING);

	private BigDecimal delegate;

	public Money(String val) {
		this.delegate = new BigDecimal(val).setScale(0, BigDecimal.ROUND_HALF_UP);
	}

	public Money(int val) {
		this.delegate = new BigDecimal(val).setScale(0, BigDecimal.ROUND_HALF_UP);
	}
	
	public static Money getMoneyThatHasZeroValue() {
		return ZERO_MONEY;
	}

	public static Money getInfiniteMoney() {
		Long x = Long.MAX_VALUE;
		return new Money(x.toString());
	}

	public Money(BigDecimal value) {
		this(value.toString());
	}

	public int intValue() {
		return delegate.intValue();
	}

	public String strValue() {
		return String.valueOf(delegate.intValue());
	}

	public Money add(Money val) {
		return new Money(delegate.add(val.delegate));
	}

	public Money subtract(Money val) {
		return new Money(delegate.subtract(val.delegate));
	}

	public BigDecimal multiply(BigDecimal val) {
		return delegate.multiply(val);
	}

	public Money multiplyAndRound(BigDecimal val) {
		BigDecimal product = delegate.multiply(val);
		return new Money(product.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	public Money divideAndReturnMoney(BigDecimal val) {
		return new Money(delegate.divide(val, 0, BigDecimal.ROUND_HALF_UP));
	}

	public BigDecimal divide(BigDecimal val) {
        return delegate.divide(val, 0, BigDecimal.ROUND_HALF_UP);
    }
	
	public Money divideByInt(int val) {
		return new Money(delegate.divide(new BigDecimal(val), 0, BigDecimal.ROUND_HALF_UP));
	}
	
	public Money percentageByRate(BigDecimal rate) {
		return new Money(delegate.multiply(rate).divide(new BigDecimal(100), 0, BigDecimal.ROUND_HALF_UP));
	}
	
	public boolean gt(Money val) {
		return compareTo(val) > 0;
	}

	public boolean gtEq(Money val) {
		return compareTo(val) >= 0;
	}

	public boolean lt(Money val) {
		return compareTo(val) < 0;
	}

	public boolean ltEq(Money val) {
		return compareTo(val) <= 0;
	}

	public boolean gtZero() {
		return gt(ZERO_MONEY);
	}

	public boolean gtEqZero() {
		return gtEq(ZERO_MONEY);
	}

	public boolean ltZero() {
		return lt(ZERO_MONEY);
	}

	public boolean ltEqZero() {
		return ltEq(ZERO_MONEY);
	}

	public int compareTo(Money val) {
		return delegate.compareTo(val.delegate);
	}

	@Override
	public int compareTo(Object o) {
		return compareTo((Money) o);
	}
}
