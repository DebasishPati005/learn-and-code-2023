package com.assignment2;

import java.util.Scanner;

public class UserInput {
    public static int takeUserInput() {
        System.out.println(Constants.INPUT_TEXT);
        Scanner scan = new Scanner(System.in);
        int inputNumber = scan.nextInt();
        while (!NumberManipulator.isValidFourDigitNumber(inputNumber)) {
            System.out.println(Constants.INVALID_NUMBER_TEXT);
            System.out.println(Constants.INPUT_TEXT);
            inputNumber = scan.nextInt();
        }
        scan.close();
        return inputNumber;
    }

}