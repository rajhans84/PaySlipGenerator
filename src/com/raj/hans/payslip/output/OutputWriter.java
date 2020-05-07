package com.raj.hans.payslip.output;

import java.util.ArrayList;
import java.util.List;

import com.raj.hans.payslip.Paysilp;

public class OutputWriter {

	public String writeCsv(Paysilp payslip) {

		List<String> data = new ArrayList<>();

		data.add(payslip.getName());
		data.add(payslip.getPaymentStartDate());
		data.add(payslip.getGrossIncome().strValue());
		data.add(payslip.getIncomeTax().strValue());
		data.add(payslip.getNetIncome().strValue());
		data.add(payslip.getSuperAmount().strValue());

		return String.join(",", data);
	}
}
