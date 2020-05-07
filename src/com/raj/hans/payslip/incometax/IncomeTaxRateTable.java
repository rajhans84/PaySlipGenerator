package com.raj.hans.payslip.incometax;

import com.raj.hans.payslip.money.Money;

public class IncomeTaxRateTable {
	private IncomeTaxRate taxRateTable[];

	public IncomeTaxRateTable(IncomeTaxRate[] taxRateTable) {
		super();
		this.taxRateTable = taxRateTable;
	}
	
	private int findTaxRange(Money income) {

		int low = 0, high = this.taxRateTable.length - 1;

		// Binary Search Range
		while (low <= high) {
			// find the mid income
			int mid = (low + high) >> 1;

			// did we found income?
			Money fromIncome = taxRateTable[mid].getLow(); 
			Money toIncome = taxRateTable[mid].getHigh();
			if (income.gtEq(fromIncome) && income.ltEq(toIncome))
				return mid;

			else if (income.lt(fromIncome))
				high = mid - 1;

			else
				low = mid + 1;
		}
		// not found
		return -1;
	}
	
	public IncomeTaxRate getByIncome(Money income) {
		if(this.findTaxRange(income) >= 0) {
			return this.taxRateTable[this.findTaxRange(income)];
		}
		return null;
	}

	public IncomeTaxRate getByIndex(int index) {
		return this.taxRateTable[index];
	}
}
