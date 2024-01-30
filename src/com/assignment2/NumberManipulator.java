package com.assignment2;

import java.util.Arrays;

public class NumberManipulator {
	public static int ascendingSort(int inputNumber) {
		String number = Integer.toString(inputNumber);
		char[] inputCharacters = number.toCharArray();
		Arrays.sort(inputCharacters);
		// int ascendingOrder = Integer.parseInt(new String(inputCharacters));
		return Integer.parseInt(new String(inputCharacters));
	}

	public static int descendingSort(int ascendingSortNumber) {
		int numberOfDigit = Constants.NO_OF_DIGITS;
		int descendingSortNumber = 0;

		while (ascendingSortNumber > 0) {
			int digit = ascendingSortNumber % 10;
			descendingSortNumber += (int) (digit * Math.pow(10, --numberOfDigit));
			ascendingSortNumber /= 10;
		}
		return descendingSortNumber;
	}

	public static boolean isValidFourDigitNumber(int inputNumber) {
		boolean isValidNumber = true;
		String number = Integer.toString(inputNumber);
		if (number.length() != Constants.NO_OF_DIGITS) {
			isValidNumber = false;
		} else if (inputNumber % 1111 == 0) {
			System.out.println(Constants.IS_NOT_KAPREKERS_CONSTANT);
			isValidNumber = false;
		}
		return isValidNumber;
	}

}
