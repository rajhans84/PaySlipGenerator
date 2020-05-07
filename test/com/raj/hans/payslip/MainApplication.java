package com.raj.hans.payslip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.raj.hans.payslip.incometax.IncomeTaxRate;
import com.raj.hans.payslip.incometax.IncomeTaxRateTable;
import com.raj.hans.payslip.input.InputInfo;
import com.raj.hans.payslip.output.OutputWriter;
import com.raj.hans.payslip.parser.PayslipInputParser;

public class MainApplication {

	public static void main(String[] args) {
		
		final  String PATH_TO_INPUT_CSV = "test/resources/input.csv";
		
		IncomeTaxRateTable taxRateTable = new IncomeTaxRateTable(
				new IncomeTaxRate[]{
				new IncomeTaxRate(0, 18200, 0, 0),
				new IncomeTaxRate(18201, 37000, 0.19, 0),
				new IncomeTaxRate(37001, 87000, 0.325, 3572),
				new IncomeTaxRate(87001, 180000, 0.37, 19822),
				new IncomeTaxRate(180001, 999999, 0.45, 54232),
		}) ;
		
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(PATH_TO_INPUT_CSV));
			String row;
			int iteration = 0;
			FileWriter csvWriter = new FileWriter("test/resources/output.csv");
			csvWriter.append("Name");
			csvWriter.append(",");
			csvWriter.append("Pay Period");
			csvWriter.append(",");
			csvWriter.append("Gross Income");
			csvWriter.append(",");
			csvWriter.append("Income tax");
			csvWriter.append(",");
			csvWriter.append("net Income");
			csvWriter.append(",");
			csvWriter.append("super");
			csvWriter.append("\n");
			while ((row = csvReader.readLine())!= null) {
				if(iteration == 0) {
			        iteration++;  
			        continue;
			    }
				PayslipInputParser payslipInputParser = new PayslipInputParser();
				InputInfo inputInfo = payslipInputParser.readCSVInput(row);
				PaySlipGenerator payslipGenerator = new PaySlipGenerator(taxRateTable);
				Paysilp payslip = payslipGenerator.generatePayslipFor(inputInfo);
				
				OutputWriter outputWriter = new OutputWriter();
				String csvRow = outputWriter.writeCsv(payslip);
				csvWriter.append(csvRow);
				csvWriter.append("\n");
			}
			csvReader.close();
			csvWriter.flush();
			csvWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
