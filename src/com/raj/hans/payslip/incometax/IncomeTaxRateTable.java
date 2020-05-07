package com.raj.hans.payslip.incometax;

public class IncomeTaxRateTable {
	private IncomeTaxRate taxRateTable[];

	public IncomeTaxRateTable(IncomeTaxRate[] taxRateTable) {
		super();
		this.taxRateTable = taxRateTable;
	}
	
	private int findTaxRange(int income) {

		int low = 0, high = this.taxRateTable.length - 1;

		// Binary Search Range
		while (low <= high) {
			// find the mid income
			int mid = (low + high) >> 1;

			// did we found income?
			if (income >= this.taxRateTable[mid].getLow() && income <= this.taxRateTable[mid].getHigh())
				return mid;

			else if (income < this.taxRateTable[mid].getLow())
				high = mid - 1;

			else
				low = mid + 1;
		}
		// not found
		return -1;
	}
	
	public IncomeTaxRate getByIncome(int income) {
		if(this.findTaxRange(income)>= 0) {
			return this.taxRateTable[this.findTaxRange(income)];
		}
		return null;
	}

	public IncomeTaxRate getByIndex(int index) {
		return this.taxRateTable[index];
	}
}
