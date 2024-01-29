package com.assignment1;

import java.util.Map;

public class CarbonFootprint {
	static void printCarbonFootprint(Map<String, Integer> emailInfo) {
		System.out.println("{\n" +
				Constants.INBOX + " : "
				+ calculateFootprint(emailInfo, Constants.INBOX, Constants.CARBON_EMISSION_OF_INBOX_MAIL) + " KG\n" +
				Constants.SENT + " : "
				+ calculateFootprint(emailInfo, Constants.SENT, Constants.CARBON_EMISSION_OF_SENT_MAIL) + " KG\n" +
				Constants.SPAM + " : "
				+ calculateFootprint(emailInfo, Constants.SPAM, Constants.CARBON_EMISSION_OF_SPAM_MAIL) + " KG\n" +
				"}");
	}

	private static double calculateFootprint(Map<String, Integer> emailInfo, String folder, double emissionFactor) {
		return emailInfo.get(folder) * emissionFactor;
	}
}