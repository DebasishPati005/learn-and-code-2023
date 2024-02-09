package com.assignment2;

import java.util.Arrays;

public class NumberManipulator {
	public static int ascendingSortOfDigits(int inputNumber) {
		String number = Integer.toString(inputNumber);
		char[] inputCharacters = number.toCharArray();
		Arrays.sort(inputCharacters);

		return Integer.parseInt(new String(inputCharacters));
	}

	public static int descendingSortOfDigits(int ascendingSortNumber) {
		int numberOfDigit = Constants.NO_OF_DIGITS;
		int descendingSortNumber = 0;

		while (ascendingSortNumber > 0) {
			int digit = ascendingSortNumber % 10;
			descendingSortNumber += (int) (digit * Math.pow(10, --numberOfDigit));
			ascendingSortNumber /= 10;
		}
		return descendingSortNumber;
	}

}
