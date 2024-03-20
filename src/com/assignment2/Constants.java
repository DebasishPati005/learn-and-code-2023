package com.assignment2;

public class Constants {
    public static final int KAPREKAR_CONSTANT = 6174;
    public static final int MAX_DIFFERENCE_ITERATION = 7;
    public static final int NO_OF_DIGITS = 4;
    public static final String INPUT_TEXT = "Enter any 4-digit number:";
    public static final String INVALID_NUMBER_TEXT = "Invalid input! Please retry.";
    public static final String IS_KAPREKERS_CONSTANT =
            "Woo Hoo! 😜 The number is Karprekar Constant";
    public static final String IS_NOT_KAPREKERS_CONSTANT =
            "Aww! 😒 Number is not a Karprekar Constant all the digits are same.";

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
