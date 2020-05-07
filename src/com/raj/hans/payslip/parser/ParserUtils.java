package com.raj.hans.payslip.parser;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserUtils {

	private static final String SUPERRATE_FORMAT = "^(\\d*.+\\d*)%$";
	
	public static BigDecimal parseAsRate(String inputVal) {
		Pattern compiledPattern = Pattern.compile(SUPERRATE_FORMAT);
		Matcher matcher = compiledPattern.matcher(inputVal);
		if(matcher.find()) {
			String rate = matcher.group(1);
			return new BigDecimal(rate);
		}
		throw new IllegalArgumentException("Invalid value supplied for Super Rate");
	}
}
