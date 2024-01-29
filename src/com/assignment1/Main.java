package com.assignment1;

import java.util.Map;

public class Main {

	public static void main(String[] args) {
		EmailInfoRetriever emailInfoRetriever = new EmailInfoRetriever();
		Map<String, Integer> emailInfo = emailInfoRetriever.retrieveEmailInfo();
		CarbonFootprint.printCarbonFootprint(emailInfo);
	}
}
