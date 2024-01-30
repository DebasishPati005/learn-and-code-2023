package com.assignment2;

public class Main {
	public static void main(String[] args) {
		int inputNumber = UserInput.takeUserInput();
		boolean isKaprekersConstant = KaprekersConstant.checkKaprekersConstant(inputNumber);
		if (isKaprekersConstant) {
			System.out.println(Constants.IS_KAPREKERS_CONSTANT);
		}
	}
}
