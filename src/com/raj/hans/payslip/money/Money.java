package com.raj.hans.payslip.money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Money implements Comparable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7300339745634312581L;
	private static final String ZERO_STRING = "0";
    private static final Money ZERO_MONEY = new Money(ZERO_STRING);
    
    private BigDecimal delegate;
    
    public Money(String val) {
        this.delegate = new BigDecimal(val);
          if(delegate.scale() > 0)
              throw new IllegalArgumentException("Money can't have scale > 0");
          delegate.setScale(0);
      }

	public int compareTo(Money val) {
		return delegate.compareTo(val.delegate);
	}

	@Override
	public int compareTo(Object o) {
		return compareTo((Money)o);
	}
}
