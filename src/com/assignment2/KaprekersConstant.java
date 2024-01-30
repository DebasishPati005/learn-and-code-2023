package com.assignment2;

public class KaprekersConstant {
	public static boolean checkKaprekersConstant(int number) {
		int ascendingSortedNumber = NumberManipulator.ascendingSort(number);
		int descendingSortedNumber = NumberManipulator.descendingSort(ascendingSortedNumber);

		int difference;
		int iterationCount = 0;
		boolean isKaprekersConstant = false;

		while (iterationCount < Constants.MAX_DIFFERENCE_ITERATION) {
			difference = Math.abs(ascendingSortedNumber - descendingSortedNumber);
			iterationCount++;
			System.out.println("\n********\nAscending: " + ascendingSortedNumber + "\niteration: "
					+ iterationCount + "\nDescending: " + descendingSortedNumber + "\nDifference: "
					+ difference + "\n*********");

			ascendingSortedNumber = NumberManipulator.ascendingSort(difference);
			descendingSortedNumber = NumberManipulator.descendingSort(ascendingSortedNumber);
			if (difference == Constants.KAPREKAR_CONSTANT) {
				isKaprekersConstant = true;
				break;
			}
		}
		return isKaprekersConstant;
	}
}